package com.github.enessetere.rpg.character;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/character")
public class CharacterController {
    private final CharacterFacade characterFacade;

    CharacterController(CharacterFacade characterFacade) {
        this.characterFacade = characterFacade;
    }

    @GetMapping
    public String getAllCharacters() {
        return characterFacade.displayAllCharacters();
    }

    @GetMapping("/{characterName}")
    public String getCharacter(@PathVariable String characterName) {
        return characterFacade.displayCharacter(characterName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCharacter(@RequestBody @Valid CharacterModel characterModel) {
        characterFacade.createNewCharacter(characterModel);
    }
}
