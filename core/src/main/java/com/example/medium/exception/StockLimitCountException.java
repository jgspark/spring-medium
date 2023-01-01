package com.example.medium.exception;

final public class StockLimitCountException extends StockException {

    public StockLimitCountException() {
    }

    public StockLimitCountException(String message) {
        super(message);
    }

    public StockLimitCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockLimitCountException(Throwable cause) {
        super(cause);
    }

    public StockLimitCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
