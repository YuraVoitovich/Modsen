package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.Lexeme;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RublesOperand extends MoneyOperand {
    private final String regex = "\\d{1,}(?:,\\d{1,})*(?:\\.\\d{1,})?р";
    private final Pattern pattern = Pattern.compile(regex);
    @Override
    public String toString() {
        return String.format("%.2f", getVal()) + "р";
    }

    public RublesOperand(double val) {
        super(val);
    }

    public RublesOperand() {

    }
    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        Matcher matcher = pattern.matcher(stringToParse);
        if (matcher.matches()) {
            double value = Double.parseDouble(stringToParse.substring(0, stringToParse.length() - 1));
            return Optional.of(new RublesOperand(value));
        } else {
            return Optional.empty();
        }
    }
}
