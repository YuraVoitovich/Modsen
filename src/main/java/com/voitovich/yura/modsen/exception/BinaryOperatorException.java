package com.voitovich.yura.modsen.exception;

public class BinaryOperatorException extends RuntimeException {
    public BinaryOperatorException() {
    }

    public BinaryOperatorException(String message) {
        super(message);
    }

    public BinaryOperatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
