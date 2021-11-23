package com.github.enessetere.rpg.character;

import com.github.enessetere.rpg.armory.Armor;
import com.github.enessetere.rpg.armory.ArmorPiece;
import com.github.enessetere.rpg.armory.Shield;
import com.github.enessetere.rpg.constants.Constants;
import com.github.enessetere.rpg.constants.TestLevelEnum;
import com.github.enessetere.rpg.mechanics.DiceRoll;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class CharacterDefences {
    private Integer dodge;
    private Integer parry;
    private Integer block;
    private Integer blockValue;
    private final Armor[] armor = new Armor[Constants.ARMOR_PIECES];
    private Shield shield;

    CharacterDefences(CharacterAttributes attributes) {
        checkDodge(attributes.getDexterity());
        checkParryAndBlock(attributes.getStrength());
    }

    public void checkDodge(Integer dexterity) {
        dodge = (int)Math.ceil(dexterity / 3.0);
    }

    public void checkParryAndBlock(Integer strength) {
        parry = (int)Math.ceil(strength / 3.0);
        block = (int)Math.ceil(strength / 2.0);
        blockValue = (int)Math.ceil(strength / 4.0);
    }

    public void equipShield(Shield shield) {
        this.shield = shield;
    }

    public void equipArmor(Armor armor) {
//        this.armor[armor.armorPiece.value] = armor;
    }

    public int defendByDodge(TestLevelEnum testLevel, Map<String, Integer> traits, Integer attackValue, ArmorPiece armorPiece) {
        int result = DiceRoll.rollADice(Constants.D20) + dodge + (Objects.nonNull(traits.get("dodge")) ? traits.get("dodge") : 0);
        return result >= testLevel.value ? 0 : attackValue/* - armor[armorPiece.value].armorValue*/;
    }

    public int defendByParry(TestLevelEnum testLevel, Map<String, Integer> traits, Integer attackValue, ArmorPiece armorPiece) {
        int result = DiceRoll.rollADice(Constants.D20) + parry + (Objects.nonNull(traits.get("parry")) ? traits.get("parry") : 0);
        return result >= testLevel.value ? 0 : attackValue/* - armor[armorPiece.value].armorValue*/;
    }

    public int defendByBlock(TestLevelEnum testLevel, Map<String, Integer> traits, Integer attackValue, ArmorPiece armorPiece) {
        int receivedDamage = attackValue/* - armor[armorPiece.value].armorValue*/;
        if(Objects.isNull(shield))
            return receivedDamage;
        int result = DiceRoll.rollADice(Constants.D20) + block + (Objects.nonNull(traits.get("block")) ? traits.get("block") : 0);
        return result < testLevel.value
                ? receivedDamage
                : receivedDamage - calculateBlockValue(traits);
    }

    private int calculateBlockValue(Map<String, Integer> traits) {
        return shield.getDamageReduction() + blockValue + (Objects.nonNull(traits.get("blockValue")) ? traits.get("blockValue") : 0);
    }

    public String toString() {
        return "D E F E N C E\nDodge: " + dodge + "\nParry: " + parry + "\nBlock: " + block + "\nBlockValue: " + blockValue
                + "\n\nA R M O R:\n"
                + Arrays.stream(armor)
                    .filter(Objects::nonNull)
                    .map(Armor::toString)
                    .collect(Collectors.joining("\n"))
                + "\nShield: " + shield
                /*+ "\nMagic resist: "
                + Arrays.stream(armor)
                    .filter(Objects::nonNull)
                    .mapToInt(x -> x.magicResist)
                    .sum()*/;
    }
}
