package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.exception.DollarsFunctionException;
import com.voitovich.yura.modsen.parser.Lexeme;
import com.voitovich.yura.modsen.parser.Operand;
import com.voitovich.yura.modsen.parser.Priority;
import com.voitovich.yura.modsen.parser.UnaryFunction;

import java.util.Optional;

public class ToDollarsFunc implements UnaryFunction {
    private final String regex = "toDollars(";

    public ToDollarsFunc() {

    }

    @Override
    public Operand calculate(Operand operand) {
        RublesOperand rublesOperand;
        try {
            rublesOperand = (RublesOperand) operand;
        } catch (ClassCastException e) {
            throw new DollarsFunctionException("Unable to convert dollars to dollars");
        }
        return new DollarsOperand(rublesOperand.val / 65.0);
    }

    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        if (stringToParse.startsWith(regex)) {

            return Optional.of(new ToDollarsFunc());
        }
        return Optional.empty();
    }


    @Override
    public Priority getPriority() {
        return Priority.LOWEST;
    }
}
