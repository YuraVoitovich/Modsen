package com.voitovich.yura.modsen.parser;

import com.voitovich.yura.modsen.exception.*;
import com.voitovich.yura.modsen.parser.impl.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Parser {

    private final List<Lexeme> lexemesTemplates;
    public Parser() {
        double exchangeRate = loadExchangeRateFromConfig("exchange.rate");
        lexemesTemplates = new ArrayList<>();
        lexemesTemplates.add(new AdditionOperator());
        lexemesTemplates.add(new SubtractionOperator());
        lexemesTemplates.add(new RublesOperand());
        lexemesTemplates.add(new DollarsOperand());
        lexemesTemplates.add(new ToDollarsFunc(exchangeRate));
        lexemesTemplates.add(new ToRublesFunc(exchangeRate));
        lexemesTemplates.add(new CloseBracket());
        lexemesTemplates.add(new OpenBracket());
    }

    public Operand parse(String input) {
        List<Lexeme> lexemes = tokenizeInput(input);
        validateBrackets(lexemes);
        List<Lexeme> postfixRepresentation = convertToPostfix(lexemes);
        return evaluatePostfix(postfixRepresentation);
    }

    private List<Lexeme> tokenizeInput(String input) {
        return Arrays.stream(input.trim()
                        .replaceAll("\\)", " ) ")
                        .replaceAll("\\(", "( ")
                        .split("\\s+"))
                .filter(str -> !str.isBlank())
                .map(this::parseLexeme)
                .toList();
    }

    private double loadExchangeRateFromConfig(String propertyName) {
        Properties config = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            config.load(inputStream);
            return Double.parseDouble(config.getProperty(propertyName));
        } catch (IOException | NumberFormatException e) {
            throw new ExchangeRateLoadingException("Failed to load the exchange rate, check the correctness of the property file", e);
        }
    }

    private Lexeme parseLexeme(String str) {
        return lexemesTemplates.stream()
                .map(lexeme -> lexeme.parse(str))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new WrongExpressionException(String.format("Unknown symbol: %s", str)));
    }

    private void validateBrackets(List<Lexeme> lexemes) {
        long openingBrackets = lexemes.stream()
                .filter(lexeme -> lexeme instanceof Function || lexeme instanceof OpenBracket)
                .count();
        long closingBrackets = lexemes.stream()
                .filter(lexeme -> lexeme instanceof CloseBracket)
                .count();
        if (openingBrackets != closingBrackets) {
            throw new BracketsException("Brackets placed incorrectly");
        }
    }

    private List<Lexeme> convertToPostfix(List<Lexeme> lexemes) {
        List<Lexeme> postfixRepresentation = new ArrayList<>();
        Stack<Operator> stack = new Stack<>();
        for (Lexeme lexeme : lexemes) {
            if (lexeme.isOperand()) {
                postfixRepresentation.add(lexeme);
            } else {
                Operator lex = (Operator) lexeme;
                if (lex.getPriority() == Priority.LOWEST) {
                    stack.push(lex);
                } else if (lex.getPriority() == Priority.HIGHEST) {
                    while (stack.peek().getPriority() != Priority.LOWEST) {
                        postfixRepresentation.add(stack.pop());
                    }
                    postfixRepresentation.add(stack.pop());
                } else {
                    while (!stack.isEmpty() && stack.peek().getPriority().compareTo(lex.getPriority()) > 0) {
                        postfixRepresentation.add(stack.pop());
                    }
                    stack.add(lex);
                }
            }
        }
        while (!stack.isEmpty()) {
            postfixRepresentation.add(stack.pop());
        }
        return postfixRepresentation;
    }

    private Operand evaluatePostfix(List<Lexeme> postfixRepresentation) {
        Stack<Operand> operands = new Stack<>();
        try {
            for (Lexeme lexeme : postfixRepresentation) {
                if (lexeme.isOperand()) {
                    operands.push((Operand) lexeme);
                } else if (lexeme instanceof OpenBracket) {
                    continue;
                } else if (lexeme instanceof BinaryOperator) {
                    try {
                        Operand operand = ((BinaryOperator) lexeme).calculate(operands.pop(), operands.pop());
                        operands.push(operand);
                    } catch (EmptyStackException e) {
                        throw new BinaryOperatorException("Wrong expression. A binary operator must take two arguments.");
                    }
                } else if (lexeme instanceof UnaryOperator) {
                    try {
                        Operand operand = ((UnaryOperator) lexeme).calculate(operands.pop());
                        operands.push(operand);
                    } catch(EmptyStackException e) {
                        throw new UnaryOperationException("Wrong expression. A unary operator must take one argument.");
                    }
                }
            }
        } catch (EmptyStackException e) {
            throw new WrongExpressionException("Wrong expression. Make sure you use the correct operators");
        }
        return operands.pop();
    }

}
