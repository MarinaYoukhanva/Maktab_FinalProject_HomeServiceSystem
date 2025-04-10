package com.freshome.exception;

public class NotCompletedOrderException extends BusinessBadRequestException {
    public NotCompletedOrderException() {
        super("the order has not been completed yet! ");
    }
}
