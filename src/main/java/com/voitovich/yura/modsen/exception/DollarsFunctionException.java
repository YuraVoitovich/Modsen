package com.voitovich.yura.modsen.exception;

public class DollarsFunctionException extends RuntimeException {
    public DollarsFunctionException() {
    }

    public DollarsFunctionException(String message) {
        super(message);
    }

    public DollarsFunctionException(String message, Throwable cause) {
        super(message, cause);
    }
}
