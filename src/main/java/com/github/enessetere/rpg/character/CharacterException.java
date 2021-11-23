package com.github.enessetere.rpg.character;

public class CharacterException extends RuntimeException{
    public CharacterException() {
        super();
    }

    public CharacterException(String message) {
        super(message);
    }

    public CharacterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharacterException(Throwable cause) {
        super(cause);
    }

    protected CharacterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
