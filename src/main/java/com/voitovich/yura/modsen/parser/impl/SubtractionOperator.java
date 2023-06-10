package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.exception.MoneyOperationException;
import com.voitovich.yura.modsen.parser.*;

import java.util.Optional;

public class SubtractionOperator implements BinaryOperator {
    private final String operator = "-";

    @Override
    public Priority getPriority() {
        return Priority.FOURTH;
    }
    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        return operator.equals(stringToParse.trim()) ? Optional.of(this) : Optional.empty();
    }

    @Override
    public Operand calculate(Operand firstOperand, Operand secondOperand) {
        double firstVal = ((MoneyOperand) firstOperand).getVal();
        double secondVal = ((MoneyOperand) secondOperand).getVal();
        if (firstOperand.getClass() != secondOperand.getClass()) {
            throw new MoneyOperationException(String
                    .format("It is not possible to subtract values in different currencies (%s - %s). Use Conversion Helper Functions)",
                            secondOperand, firstOperand));
        }
        if (firstOperand.getClass() == DollarsOperand.class) {
            return new DollarsOperand(secondVal - firstVal);

        } else {
            return new RublesOperand(secondVal - firstVal);
        }
    }
}
