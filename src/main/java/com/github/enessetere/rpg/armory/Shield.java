package com.github.enessetere.rpg.armory;

import com.github.enessetere.rpg.mechanics.DiceRoll;

public class Shield {
    private Integer damageReductionDice;
    private Integer additionalBonus;

    private Shield() {}

    protected void setDamageReductionDice(Integer damageReductionDice) {
        this.damageReductionDice = damageReductionDice;
    }

    public Integer getDamageReductionDice() {
        return damageReductionDice;
    }

    protected void setAdditionalBonus(Integer additionalBonus) {
        this.additionalBonus = additionalBonus;
    }

    public Integer getAdditionalBonus() {
        return additionalBonus;
    }

    public Integer getDamageReduction() {
        return DiceRoll.rollADice(damageReductionDice) + additionalBonus;
    }

    @Override
    public String toString() {
        return "D" + damageReductionDice + " + " + additionalBonus;
    }

    public static class Builder {
        private final Shield shield;

        public Builder() {
            this.shield = new Shield();
        }

        public Builder damageReductionDice(Integer damageReductionDice) {
            this.shield.setDamageReductionDice(damageReductionDice);
            return this;
        }

        public Builder additionalBonus(Integer additionalBonus) {
            this.shield.setAdditionalBonus(additionalBonus);
            return this;
        }

        public Shield build() {
            return shield;
        }
    }
}
