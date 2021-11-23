package com.github.enessetere.rpg.character;

import com.github.enessetere.rpg.armory.Armor;
import com.github.enessetere.rpg.armory.ArmorPiece;
import com.github.enessetere.rpg.armory.Shield;
import com.github.enessetere.rpg.constants.TestLevelEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

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

    private Character() {}

    public void equipArmor(Armor armor) {
        this.defences.equipArmor(armor);
    }

    public void equipShield(Shield shield) {
        defences.equipShield(shield);
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
        private Character character;

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

        public Character build() {
            character.setDefences(new CharacterDefences(character.attributes));
            character.setStatistics(new CharacterStatistics(character.attributes));
            character.traits = new HashMap<>();
            return character;
        }
    }
}
