package com.github.enessetere.rpg.character;

import java.util.Objects;

class CharacterStatistics {
    private int maxHealthPoints;
    private int maxMagicPoints;
    private int maxAttackPoints;
    private int actualHealthPoints;
    private int actualMagicPoints;
    private int actualAttackPoints;

    CharacterStatistics(CharacterAttributes attributes) {
        checkMaxHealthPoints(attributes.getConstitution());
        checkMaxMagicPoints(attributes.getWisdom());
        checkMaxAttackPoints(attributes.getConstitution());
        actualHealthPoints = maxHealthPoints;
    }

    void checkMaxHealthPoints(Integer constitution) {
        int newValue = 5 * constitution + 5;
        actualHealthPoints += newValue - maxHealthPoints;
        maxHealthPoints = newValue;
    }

    void checkMaxMagicPoints(Integer wisdom) {
        int newValue = 3 * wisdom + 10;
        actualMagicPoints += newValue - maxMagicPoints;
        maxMagicPoints = newValue;
    }

    void checkMaxAttackPoints(Integer constitution) {
        int newValue = 3 * constitution + 5;
        actualAttackPoints += newValue - maxAttackPoints;
        maxAttackPoints = newValue;
    }

    @Override
    public String toString() {
        return "S T A T I S T I C S"
                + "\nHealth Points: " + actualHealthPoints + "/" + maxHealthPoints
                + "\nMagic Points: " + actualMagicPoints + "/" + maxMagicPoints
                + "\nAttack Points: " + actualAttackPoints + "/" + maxAttackPoints;
    }
}
