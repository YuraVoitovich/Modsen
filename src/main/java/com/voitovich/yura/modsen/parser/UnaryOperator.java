package com.voitovich.yura.modsen.parser;

public interface UnaryOperator extends Operator {
    Operand calculate(Operand operand);
}
