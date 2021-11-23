package com.github.enessetere.rpg.handlers;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Getter(AccessLevel.PUBLIC)
@Builder
class ErrorMessage {
    private String message;
    private List<String> details;
}
