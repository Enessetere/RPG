package com.github.enessetere.rpg.armory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
public class Armor extends Item implements Equipment {

    private ArmorPiece armorPiece;
    private int armorValue;
    private Map<String, Integer> modifiers;

    private Armor() {}

    @Override
    public void equip(String name) {

    }

    @Override
    public void remove(String name) {

    }

    @Override
    public void drop(String name, String target) {


    }

    @Override
    public String toString() {
        return "";
    }

    public static class Builder {
        private final Armor armor;

        public Builder() {
            this.armor = new Armor();
        }

        public Builder name(String name) {
            this.armor.setName(name);
            return this;
        }

        public Builder value(int value) {
            this.armor.setValue(value);
            return this;
        }

        public Builder requirements(Requirements requirements) {
            this.armor.setRequirements(requirements);
            return this;
        }

        public Builder armorPiece(ArmorPiece armorPiece) {
            this.armor.setArmorPiece(armorPiece);
            return this;
        }

        public Builder armorValue(int armorValue) {
            this.armor.setArmorValue(armorValue);
            return this;
        }

        public Builder modifiers(Map<String, Integer> modifiers) {
            this.armor.setModifiers(modifiers);
            return this;
        }

        public Armor build() {
            this.armor.setQuantity(1);
            return this.armor;
        }
    }
}
