package com.voitovich.yura.modsen.exception;

public class MoneyOperationException extends RuntimeException {
    public MoneyOperationException() {
    }

    public MoneyOperationException(String message) {
        super(message);
    }

    public MoneyOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
