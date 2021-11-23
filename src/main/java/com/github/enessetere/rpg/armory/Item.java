package com.github.enessetere.rpg.armory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PROTECTED)
abstract class Item {
    protected String name;
    protected int quantity;
    protected int value;
    protected Requirements requirements;

    public abstract void drop(String name, String target);
}
