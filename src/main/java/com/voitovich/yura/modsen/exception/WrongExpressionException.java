package com.voitovich.yura.modsen.exception;

public class WrongExpressionException extends RuntimeException {
    public WrongExpressionException() {
        super();
    }

    public WrongExpressionException(String message) {
        super(message);
    }

    public WrongExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
