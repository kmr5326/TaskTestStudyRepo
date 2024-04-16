package com.github.prgrms.errors;

public class NotCompletedOrderException extends RuntimeException{
    public NotCompletedOrderException(String message) {
        super(message);
    }

    public NotCompletedOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
