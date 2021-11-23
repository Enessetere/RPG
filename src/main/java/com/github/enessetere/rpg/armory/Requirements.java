package com.github.enessetere.rpg.armory;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
class Requirements {
    private Requirements() {}

    private String firstRequirement;
    private int firstRequirementValue;
    private String secondRequirement;
    private int secondRequirementValue;

    @Override
    public String toString() {
        return Objects.nonNull(firstRequirement)
                ? firstRequirement + ": " + firstRequirementValue +
                (Objects.nonNull(secondRequirement)
                        ? "\n" + secondRequirement + ": " + secondRequirementValue : "")
                : "";
    }

    public static class Builder{
        private final Requirements requirement;

        public Builder() {
            this.requirement = new Requirements();
        }

        public Builder firstRequirement(String name, int value) {
            this.requirement.setFirstRequirement(name);
            this.requirement.setFirstRequirementValue(value);
            return this;
        }

        public Builder secondRequirement(String name, int value) {
            this.requirement.setSecondRequirement(name);
            this.requirement.setSecondRequirementValue(value);
            return this;
        }

        public Requirements build() {
            return this.requirement;
        }
    }
}
