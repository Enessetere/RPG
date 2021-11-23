package com.github.enessetere.rpg.armory;

public enum ArmorPiece {
    HEAD(0), CHEST(1), SHOULDER(2), HANDS(3), PANTS(4), FEET(5);

    public final int value;
    private ArmorPiece(int value) {
        this.value = value;
    }
}
