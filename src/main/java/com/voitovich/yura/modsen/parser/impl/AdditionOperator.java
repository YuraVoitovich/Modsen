package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.BinaryOperator;
import com.voitovich.yura.modsen.parser.Lexeme;
import com.voitovich.yura.modsen.parser.Operand;
import com.voitovich.yura.modsen.parser.Operator;

import java.util.Optional;

public class AdditionOperator implements BinaryOperator {

    private final String operator = "+";
    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        return operator.equals(stringToParse) ? Optional.of(this) : Optional.empty();
    }

    @Override
    public Operand calculate(Operand firstOperand, Operand SecondOperand) {
        return null;
    }
}
