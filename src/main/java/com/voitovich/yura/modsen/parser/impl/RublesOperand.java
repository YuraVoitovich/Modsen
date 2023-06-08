package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.Lexeme;
import com.voitovich.yura.modsen.parser.Operand;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RublesOperand extends MoneyOperand {

    private final String regex = "";
    @Override
    public String toString() {
        return value.toString() + "р";
    }

    public RublesOperand() {
        super();
        pattern = Pattern.compile(regex);
    }

    @Override
    protected MoneyOperand create(Number val) {
        RublesOperand rublesOperand = new RublesOperand();
        rublesOperand.value = val;
        return rublesOperand;
    }


}
