package com.github.enessetere.rpg.character;

import com.github.enessetere.rpg.armory.Item;
import com.github.enessetere.rpg.constants.Constants;
import com.github.enessetere.rpg.constants.TestLevelEnum;
import com.github.enessetere.rpg.mechanics.DiceRoll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CharacterFacade {
    private final List<Character> players;

    private CharacterFacade() {
        players = new ArrayList<>();
    }

    public void createNewCharacter(CharacterModel characterModel) {
        if (findCharacterByName(characterModel.getName()).isPresent()) {
            String message = String.format("Cannot create character with name '%s'", characterModel.getName());
            log.error(message);
            throw new CharacterException(message);
        }
        players.add(createCharacterInstance(characterModel));
        log.info(String.format("Character '%s' has been created.", characterModel.getName()));
    }

    private Optional<Character> findCharacterByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst();
    }

    private Character createCharacterInstance(CharacterModel model) {
        return new Character.Builder()
                .name(model.getName())
                .age(model.getAge())
                .sex(model.getSex().charAt(0))
                .attributes(
                        model.getStr(),
                        model.getDex(),
                        model.getPer(),
                        model.getCon(),
                        model.getCha(),
                        model.getWis()
                )
                .build();
    }


    public String displayCharacter(String characterName) {
        return findCharacterByName(characterName).toString();
    }

    public String displayAllCharacters() {
        if(players.size() == 0) {
            throw new CharacterException("No characters added to the game!");
        }
        return players.stream()
                .map(Character::toString)
                .collect(Collectors.joining(";"));
    }

    public boolean addToInventory(Item item, String name) {
        Character character = findCharacterByName(name)
                .orElseThrow(() -> new CharacterException(String.format("Cannot create character with name '%s'", name)));
        return character.addToInventory(item);
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
