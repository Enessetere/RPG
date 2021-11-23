package com.github.enessetere.rpg.mechanics;

public enum HitIndicatorDice {
    LEFT_FEET(1),
    RIGHT_FEET(2),
    LEFT_LEG(3),
    RIGHT_LEG(4),
    LOWER_CHEST(5),
    MIDDLE_CHEST(6),
    UPPER_CHEST(7),
    LEFT_HAND(8),
    RIGHT_HAND(9),
    LEFT_ARM(10),
    RIGHT_ARM(11),
    HEAD(12);

    public final int hitPlace;
    private HitIndicatorDice(int hitPlace) {
        this.hitPlace = hitPlace;
    }
}
