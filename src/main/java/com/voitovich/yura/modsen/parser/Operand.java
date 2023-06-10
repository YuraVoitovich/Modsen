package com.voitovich.yura.modsen.parser;

public interface Operand extends Lexeme {
    @Override
    default boolean isOperand() {
        return true;
    }
}
