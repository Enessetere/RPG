package com.github.enessetere.rpg.character;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PRIVATE)
class CharacterModel {
    @NotBlank(message = "Name is mandatory.")
    @Size(min = 3, max = 15, message = "Name can be 3 to 15 characters long.")
    @Pattern(regexp = "^[A-Za-z]+", message = "Name can contain only letters.")
    private String name;

    @Min(value = 15, message = "Character can't by younger than 15 years.")
    @Max(value = 99, message = "Character can't be older than 99 years.")
    private int age;

    @NotBlank(message = "Sex is mandatory.")
    @Size(min = 1, max = 1, message = "Sex can contain only one letter (M for Male, F for Female, other letter will set sex as Unknown.")
    private String sex;

    @Min(value = 1, message = "Minimal STR attribute level is 1.")
    @Max(value = 10, message = "Maximal STR attribute level is 10.")
    private int str;

    @Min(value = 1, message = "Minimal DEX attribute level is 1.")
    @Max(value = 10, message = "Maximal DEX attribute level is 10.")
    private int dex;

    @Min(value = 1, message = "Minimal PER attribute level is 1.")
    @Max(value = 10, message = "Maximal PER attribute level is 10.")
    private int per;

    @Min(value = 1, message = "Minimal CON attribute level is 1.")
    @Max(value = 10, message = "Maximal CON attribute level is 10.")
    private int con;

    @Min(value = 1, message = "Minimal CHA attribute level is 1.")
    @Max(value = 10, message = "Maximal CHA attribute level is 10.")
    private int cha;

    @Min(value = 1, message = "Minimal WIS attribute level is 1.")
    @Max(value = 10, message = "Maximal WIS attribute level is 10.")
    private int wis;
}
