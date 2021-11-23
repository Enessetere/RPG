package com.github.enessetere.rpg.handlers;

import com.github.enessetere.rpg.character.CharacterController;
import com.github.enessetere.rpg.character.CharacterException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(assignableTypes = CharacterController.class)
public class CharacterExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CharacterException.class)
    public ErrorMessage handleCharacterException(CharacterException exception) {
        return ErrorMessage.builder()
                .message("Character exception occurred. Please check following details.")
                .details(List.of(exception.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleValidationException(MethodArgumentNotValidException exception) {
        return ErrorMessage.builder()
                .message("Character creation exception occurred. Please check following details.")
                .details(getValidationDetails(exception))
                .build();
    }

    private List<String> getValidationDetails(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
    }
}
