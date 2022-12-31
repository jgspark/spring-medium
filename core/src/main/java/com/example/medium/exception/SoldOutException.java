package com.example.medium.exception;

final public class SoldOutException extends RuntimeException {

    public SoldOutException() {
    }

    public SoldOutException(String message) {
        super(message);
    }

    public SoldOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoldOutException(Throwable cause) {
        super(cause);
    }
}
