package com.freshome.exception;

public class PaymentSessionExpiredException extends BusinessBadRequestException {
    public PaymentSessionExpiredException() {
        super("the payment session expired! please try again.");
    }
}
