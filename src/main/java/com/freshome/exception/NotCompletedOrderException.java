package com.freshome.exception;

public class NotCompletedOrderException extends RuntimeException {
    public NotCompletedOrderException() {
        super("the order has not been completed yet! ");
    }
}
