package com.voitovich.yura.modsen.parser;

import java.util.Optional;

public interface Lexeme {
    Optional<Lexeme> parse(String stringToParse);
}
