package com.voitovich.yura.modsen.parser.impl;

import com.voitovich.yura.modsen.parser.BinaryFunction;
import com.voitovich.yura.modsen.parser.Function;
import com.voitovich.yura.modsen.parser.Lexeme;
import com.voitovich.yura.modsen.parser.Operand;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToRublesFunc implements BinaryFunction {
    private final String regex = "";
    private final Pattern pattern = Pattern.compile(regex);
    private final String paramsRegex = "";

    private final Pattern paramsPattern = Pattern.compile(paramsRegex);

    private String parameters;

    public ToRublesFunc(String parameters) {
        this.parameters = parameters;
    }

    @Override
    public Optional<Lexeme> parse(String stringToParse) {
        Matcher matcher = pattern.matcher(stringToParse);
        String params = "";
        if (matcher.matches()) {
            Matcher paramsMatcher = paramsPattern.matcher(stringToParse);
            if (paramsMatcher.find()) {
                parameters = paramsMatcher.group();
            }
            return Optional.of(new ToRublesFunc(params));
        }
        return Optional.empty();
    }

    @Override
    public Operand calculate(Operand firstOperand, Operand SecondOperand) {
        return null;
    }
}
