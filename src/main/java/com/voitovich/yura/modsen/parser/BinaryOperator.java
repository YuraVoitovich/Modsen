package com.voitovich.yura.modsen.parser;

public interface BinaryOperator extends Operator {
    Operand calculate(Operand firstOperand, Operand secondOperand);
}
