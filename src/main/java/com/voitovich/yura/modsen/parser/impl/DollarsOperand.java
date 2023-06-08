package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.Operand;
import com.voitovich.yura.modsen.parser.Operator;

import java.util.regex.Pattern;

public class DollarsOperand extends MoneyOperand {
    private final String regex = "";
    @Override
    public String toString() {
        return "$" + value.toString();
    }

    public DollarsOperand() {
        super();
        pattern = Pattern.compile(regex);
    }

    @Override
    protected MoneyOperand create(Number val) {
        DollarsOperand dollarsOperand = new DollarsOperand();
        dollarsOperand.value = val;
        return dollarsOperand;
    }

}
