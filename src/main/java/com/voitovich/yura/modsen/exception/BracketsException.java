package com.voitovich.yura.modsen.exception;

public class BracketsException extends RuntimeException {
    public BracketsException() {
        super();
    }

    public BracketsException(String message) {
        super(message);
    }

    public BracketsException(String message, Throwable cause) {
        super(message, cause);
    }
}
