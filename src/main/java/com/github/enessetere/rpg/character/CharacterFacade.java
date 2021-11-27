package com.github.enessetere.rpg.character;

import com.github.enessetere.rpg.constants.Constants;
import com.github.enessetere.rpg.constants.TestLevelEnum;
import com.github.enessetere.rpg.mechanics.DiceRoll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CharacterFacade {
    private final List<Character> players;

    private CharacterFacade() {
        players = new ArrayList<>();
    }

    public void createNewCharacter(CharacterModel characterModel) {
        Character character = new Character.Builder()
                .name(characterModel.getName())
                .age(characterModel.getAge())
                .sex(characterModel.getSex().charAt(0))
                .attributes(characterModel.getStr(), characterModel.getDex(), characterModel.getPer(), characterModel.getCon(), characterModel.getCha(), characterModel.getWis())
                .build();
        if (players.stream().noneMatch(player -> player.getName().equals(character.getName()))) {
            players.add(character);
            log.info(String.format("Character '%s' has been created.", character.getName()));
        } else {
            String message = String.format("Cannot create character with name '%s'", character.getName());
            log.error(message);
            throw new CharacterException(message);
        }
    }

    public String displayCharacter(String characterName) {
        Character character = players.stream()
                .filter(player -> player.getName().equals(characterName))
                .findFirst()
                .orElseThrow(() -> new CharacterException(String.format("Cannot find character with given name '%s'!", characterName)));
        return character.toString();
    }

    public String displayAllCharacters() {
        if(players.size() == 0) {
            throw new CharacterException("No characters added to the game!");
        }
        return players.stream()
                .map(Character::toString)
                .collect(Collectors.joining(";"));
    }

    public boolean attackWithStrength(String name, TestLevelEnum testLevel) {
        Character character = players.stream().filter(player -> player.getName().equals(name)).findFirst().orElseThrow(() -> new CharacterException("No character with name '" + name + "'"));
        return testLevel.value <= (DiceRoll.rollADice(Constants.D20) + character.getAttributes().getStrength());
    }

    public boolean attackWithDexterity(String name, TestLevelEnum testLevel) {
        Character character = players.stream().filter(player -> player.getName().equals(name)).findFirst().orElseThrow(() -> new CharacterException("No character with name '" + name + "'"));
        return testLevel.value <= (DiceRoll.rollADice(Constants.D20) + character.getAttributes().getDexterity());
    }

    public boolean attackWithConstitution(String name, TestLevelEnum testLevel) {
        Character character = players.stream().filter(player -> player.getName().equals(name)).findFirst().orElseThrow(() -> new CharacterException("No character with name '" + name + "'"));
        return testLevel.value <= (DiceRoll.rollADice(Constants.D20) + character.getAttributes().getConstitution());
    }

    public boolean attackWithSpell(String name, TestLevelEnum testLevel) {
        Character character = players.stream().filter(player -> player.getName().equals(name)).findFirst().orElseThrow(() -> new CharacterException("No character with name '" + name + "'"));
        return testLevel.value <= (DiceRoll.rollADice(Constants.D20) + character.getAttributes().getWisdom());
    }
}
