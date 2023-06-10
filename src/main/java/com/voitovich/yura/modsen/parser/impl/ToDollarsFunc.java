package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.exception.DollarsFunctionException;
import com.voitovich.yura.modsen.parser.Lexeme;
import com.voitovich.yura.modsen.parser.Operand;
import com.voitovich.yura.modsen.parser.Priority;
import com.voitovich.yura.modsen.parser.UnaryFunction;

import java.util.Optional;

public class ToDollarsFunc implements UnaryFunction {
    private final String regex = "toDollars(";
    private final double exchangeRate;
    public ToDollarsFunc() {
        this.exchangeRate = 0.0;
    }
    public ToDollarsFunc(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public Operand calculate(Operand operand) {
        RublesOperand rublesOperand;
        try {
            rublesOperand = (RublesOperand) operand;
        } catch (ClassCastException e) {
            throw new DollarsFunctionException("Unable to convert dollars to dollars");
        }
        return new DollarsOperand(rublesOperand.getVal() / exchangeRate);
    }

    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        if (stringToParse.startsWith(regex)) {

            return Optional.of(this);
        }
        return Optional.empty();
    }


    @Override
    public Priority getPriority() {
        return Priority.LOWEST;
    }
}
