package com.voitovich.yura.modsen.exception;

public class UnaryOperationException extends RuntimeException {
    public UnaryOperationException() {
    }

    public UnaryOperationException(String message) {
        super(message);
    }

    public UnaryOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
