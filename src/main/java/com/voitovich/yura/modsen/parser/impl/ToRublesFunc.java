package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.exception.RublesFunctionException;
import com.voitovich.yura.modsen.parser.Lexeme;
import com.voitovich.yura.modsen.parser.Operand;
import com.voitovich.yura.modsen.parser.Priority;
import com.voitovich.yura.modsen.parser.UnaryFunction;

import java.util.Optional;

public class ToRublesFunc implements UnaryFunction {
    private final String regex = "toRubles(";

    public ToRublesFunc() {

    }

    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        if (stringToParse.startsWith(regex)) {
            return Optional.of(new ToRublesFunc());
        }
        return Optional.empty();
    }

    @Override
    public Priority getPriority() {
        return Priority.LOWEST;
    }

    @Override
    public Operand calculate(Operand operand) {
        DollarsOperand dollarsOperand = null;
        try {
            dollarsOperand = (DollarsOperand) operand;
        } catch (ClassCastException e) {
            throw new RublesFunctionException("Unable to convert rubles to rubles");
        }
        return new RublesOperand(dollarsOperand.getVal() * 65.0);
    }
}
