package com.voitovich.yura.modsen.parser;

import com.voitovich.yura.modsen.exception.BracketsException;
import com.voitovich.yura.modsen.exception.WrongExpressionException;
import com.voitovich.yura.modsen.parser.impl.*;

import java.util.*;

public class Parser {

    private final List<Lexeme> lexemesTemplates;
    public Parser() {
        lexemesTemplates = new ArrayList<>();
        lexemesTemplates.add(new AdditionOperator());
        lexemesTemplates.add(new SubtractionOperator());
        lexemesTemplates.add(new RublesOperand());
        lexemesTemplates.add(new DollarsOperand());
        lexemesTemplates.add(new ToDollarsFunc());
        lexemesTemplates.add(new ToRublesFunc());
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

    private Lexeme parseLexeme(String str) {
        return lexemesTemplates.stream()
                .map(lexeme -> lexeme.parse(str))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new WrongExpressionException("Please check the correctness of the expression"));
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
        for (Lexeme lexeme : postfixRepresentation) {
            if (lexeme.isOperand()) {
                operands.push((Operand) lexeme);
            } else if (lexeme instanceof OpenBracket) {
                continue;
            } else if (lexeme instanceof BinaryOperator) {
                Operand operand = ((BinaryOperator) lexeme).calculate(operands.pop(), operands.pop());
                operands.push(operand);
            } else if (lexeme instanceof UnaryOperator) {
                Operand operand = ((UnaryOperator) lexeme).calculate(operands.pop());
                operands.push(operand);
            }
        }
        return operands.pop();
    }

}
