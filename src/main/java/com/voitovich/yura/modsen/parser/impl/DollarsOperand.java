package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.Lexeme;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DollarsOperand extends MoneyOperand {
    private final String regex = "\\$\\d{1,}(?:(,)\\d{1,})*(?:(\\.)\\d{1,})?";
    private final Pattern pattern = Pattern.compile(regex);
    @Override
    public String toString() {
        return "$" + String.format("%.2f", getVal());
    }

    public DollarsOperand() {

    }

    public DollarsOperand(double val) {
        super(val);
    }

    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        Matcher matcher = pattern.matcher(stringToParse);
        if (matcher.matches()) {
            double value = Double.parseDouble(stringToParse.substring(1));
            return Optional.of(new DollarsOperand(value));
        } else {
            return Optional.empty();
        }
    }
}
