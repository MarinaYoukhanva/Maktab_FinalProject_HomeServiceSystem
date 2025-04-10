package com.freshome.exception;

public class PaidOrderException extends BusinessBadRequestException {
    public PaidOrderException() {
        super("the order is already paid! ");
    }
}
