package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.Lexeme;
import com.voitovich.yura.modsen.parser.Operand;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MoneyOperand implements Operand {
    protected Pattern pattern;

    protected Number value;

    @Override
    public String toString() {
        return value.toString() + "р";
    }
    protected abstract MoneyOperand create(Number val);
    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        Matcher matcher = pattern.matcher(stringToParse);
        Number val = null;
        if (matcher.matches()) {
            stringToParse = stringToParse.substring(0, stringToParse.length() - 1);
            try {
                val = Integer.valueOf(stringToParse);
            } catch (NumberFormatException e) {
                try {
                    val = Double.valueOf(stringToParse);
                } catch (NumberFormatException exception) {
                    return Optional.empty();
                }
            }
            return Optional.of(create(val));
        }
        return Optional.empty();
    }
}
