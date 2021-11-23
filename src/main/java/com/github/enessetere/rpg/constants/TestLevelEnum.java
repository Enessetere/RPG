package com.github.enessetere.rpg.constants;

public enum TestLevelEnum {
    TRIVIAL(2),
    EASY(5),
    MEDIUM(10),
    HARD(15),
    VERY_HARD(20),
    IMPOSSIBLE(25);

    public final Integer value;
    private TestLevelEnum(Integer value) {
        this.value = value;
    }
}
