package com.github.enessetere.rpg.character;

import java.util.Objects;

class CharacterAttributes {
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer perception;
    private Integer charisma;
    private Integer wisdom;

    private CharacterAttributes() {}

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public Integer getCharisma() {
        return charisma;
    }

    public void setPerception(int perception) {
        this.perception = perception;
    }

    public Integer getPerception() {
        return perception;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public Integer getWisdom() {
        return wisdom;
    }

    @Override
    public String toString() {
        return "A T T R I B U T E S"
                + "\nStrength: " + strength
                + "\nDexterity: " + dexterity
                + "\nConstitution: " + constitution
                + "\nCharisma: " + charisma
                + "\nWisdom: " + wisdom
                + "\nPerception: " + perception;
    }

    public static class Builder {
        private static final int BASE_ATTRIBUTE = 1;
        private final CharacterAttributes attributes;

        public Builder() {
            attributes = new CharacterAttributes();
        }

        public Builder strength(int strength) {
            attributes.setStrength(strength);
            return this;
        }

        public Builder dexterity(int dexterity) {
            attributes.setDexterity(dexterity);
            return this;
        }

        public Builder constitution(int constitution) {
            attributes.setConstitution(constitution);
            return this;
        }

        public Builder charisma(int charisma) {
            attributes.setCharisma(charisma);
            return this;
        }

        public Builder perception(int perception) {
            attributes.setPerception(perception);
            return this;
        }

        public Builder wisdom(int wisdom) {
            attributes.setWisdom(wisdom);
            return this;
        }

        public CharacterAttributes build() {
            if(Objects.isNull(attributes.getStrength()))
                attributes.setStrength(BASE_ATTRIBUTE);
            if(Objects.isNull(attributes.getDexterity()))
                attributes.setDexterity(BASE_ATTRIBUTE);
            if(Objects.isNull(attributes.getPerception()))
                attributes.setPerception(BASE_ATTRIBUTE);
            if(Objects.isNull(attributes.getWisdom()))
                attributes.setWisdom(BASE_ATTRIBUTE);
            if(Objects.isNull(attributes.getConstitution()))
                attributes.setConstitution(BASE_ATTRIBUTE);
            if(Objects.isNull(attributes.getCharisma()))
                attributes.setCharisma(BASE_ATTRIBUTE);
            return attributes;
        }
    }
}
