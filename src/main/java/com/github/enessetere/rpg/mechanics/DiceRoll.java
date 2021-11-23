package com.github.enessetere.rpg.mechanics;

import com.github.enessetere.rpg.constants.Constants;

public class DiceRoll {
    public static Integer rollADice(Integer value) {
        return  Constants.RANDOM.nextInt(value) + 1;
    }
}
