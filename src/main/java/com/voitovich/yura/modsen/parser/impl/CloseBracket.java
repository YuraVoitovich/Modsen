package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.Lexeme;
import com.voitovich.yura.modsen.parser.Operator;
import com.voitovich.yura.modsen.parser.Priority;

import java.util.Optional;

public class CloseBracket implements Operator {

    private final String regex = ")";
    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        return stringToParse.trim().equals(regex) ? Optional.of(this) : Optional.empty();
    }

    @Override
    public Priority getPriority() {
        return Priority.HIGHEST;
    }
}
