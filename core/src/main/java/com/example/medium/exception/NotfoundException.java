package com.example.medium.exception;

public class NotfoundException extends RuntimeException {

    public NotfoundException() {
    }

    public NotfoundException(Class<?> type) {
        super(type.getName());
    }

    public NotfoundException(Class<?> type, String message) {
        super(type.getName() + message);
    }

    public NotfoundException(String message) {
        super(message);
    }

    public NotfoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotfoundException(Throwable cause) {
        super(cause);
    }

    public NotfoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
