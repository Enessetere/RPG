package com.github.enessetere.rpg.character;

import com.github.enessetere.rpg.armory.*;
import com.github.enessetere.rpg.constants.TestLevelEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter(AccessLevel.PRIVATE)
@Getter
class Character {
    private String name;
    private SexEnum sex;
    private Integer age;
    private CharacterAttributes attributes;
    private CharacterStatistics statistics;
    private CharacterDefences defences;
    private Map<String, Integer> traits;
    private List<Item> inventory;

    private Character() {
        inventory = new LinkedList<>();
    }

    private void equipArmor(Armor armor) {
        Item unequipped = this.defences.equipArmor(armor);
        if(Objects.nonNull(unequipped))
            inventory.add(unequipped);
    }

    private void equipShield(Shield shield) {
        Item unequipped = defences.equipShield(shield);
        if(Objects.nonNull(unequipped))
            inventory.add(unequipped);
    }

    public void addTrait(String key, Integer value) {
        traits.put(key, value);
    }

    public boolean dodge(Integer attackValue) {
        return defences.defendByDodge(TestLevelEnum.VERY_HARD, traits, 15, ArmorPiece.CHEST) == 0;
    }

    public boolean parry(Integer attackValue) {
        return defences.defendByParry(TestLevelEnum.VERY_HARD, traits, 15, ArmorPiece.CHEST) == 0;
    }

    public boolean block(Integer attackValue) {
        return defences.defendByBlock(TestLevelEnum.VERY_HARD, traits, 15, ArmorPiece.CHEST) < 13;
    }

    public boolean addToInventory(Item item) {
        return inventory.add(item);
    }

    public boolean equipItem(Item piece) {
        Item item = findItemInInventory(piece);
        if(!checkItemRequirements(piece))
            return false;
        if (!item.reduceQuantityBy(1))
            inventory.remove(item);
        if (item instanceof Armor) {
            equipArmor((Armor) item);
        } else if (item instanceof Shield) {
            equipShield((Shield) item);
        }
        return false;
    }

    private Item findItemInInventory(Item piece) {
        return inventory.stream()
                .filter(item -> item.equals(piece))
                .findFirst()
                .orElseThrow(() -> new CharacterException("Cannot find this item: " + piece.getName()));
    }

    private boolean checkItemRequirements(Item item) {
        Requirements requirements = item.getRequirements();
        return checkRequirements(requirements.getFirstRequirement(), requirements.getFirstRequirementValue())
                && checkRequirements(requirements.getSecondRequirement(), requirements.getSecondRequirementValue());
    }

    private boolean checkRequirements(String attributeName, Integer attributeValue) {
        return switch (attributeName) {
            case "STR" -> attributes.getStrength() > attributeValue;
            case "DEX" -> attributes.getDexterity() > attributeValue;
            case "PER" -> attributes.getPerception() > attributeValue;
            case "CON" -> attributes.getConstitution() > attributeValue;
            case "WIS" -> attributes.getWisdom() > attributeValue;
            case "CHA" -> attributes.getCharisma() > attributeValue;
            default -> true;
        };
    }

    @Override
    public String toString() {
        return "C H A R A C T E R\n" +
                "Name: " + name + "\n" +
                "Sex: " + sex + "\n" +
                "Age: " + age + "\n" +
                "\n" + attributes + "\n" +
                "\n" + statistics + "\n" +
                "\n" + defences + "\n" +
                "\nTraits: " + traits + "\n";
    }

    public static class Builder {
        private final Character character;

        public Builder() {
            this.character = new Character();
        }

        public Builder name(String name) {
            character.setName(name);
            return this;
        }

        public Builder sex(char sex) {
            character.setSex(sex == 'M' ? SexEnum.MALE : (sex == 'F' ? SexEnum.FEMALE : SexEnum.UNKNOWN));
            return this;
        }

        public Builder age(Integer age) {
            character.setAge(age);
            return this;
        }

        public Builder attributes(Integer str, Integer dex, Integer per, Integer con, Integer cha, Integer wis) {
            character.setAttributes(new CharacterAttributes.Builder()
                    .strength(str)
                    .dexterity(dex)
                    .perception(per)
                    .constitution(con)
                    .charisma(cha)
                    .wisdom(wis)
                    .build());
            return this;
        }

        public Builder item(Item item) {
            character.inventory.add(item);
            return this;
        }

        public Character build() {
            character.setDefences(new CharacterDefences(character.attributes));
            character.setStatistics(new CharacterStatistics(character.attributes));
            character.traits = new HashMap<>();
            return character;
        }
    }
}
