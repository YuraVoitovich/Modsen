package com.voitovich.yura.modsen.exception;

public class ExchangeRateLoadingException extends RuntimeException {
    public ExchangeRateLoadingException() {
    }

    public ExchangeRateLoadingException(String message) {
        super(message);
    }

    public ExchangeRateLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
