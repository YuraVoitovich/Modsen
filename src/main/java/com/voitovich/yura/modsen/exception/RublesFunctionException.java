package com.voitovich.yura.modsen.exception;

public class RublesFunctionException extends RuntimeException {
    public RublesFunctionException() {
    }

    public RublesFunctionException(String message) {
        super(message);
    }

    public RublesFunctionException(String message, Throwable cause) {
        super(message, cause);
    }
}
