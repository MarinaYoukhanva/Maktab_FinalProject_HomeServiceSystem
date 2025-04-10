package com.freshome.exception;

public class InvalidPriceException extends BusinessBadRequestException {
    public InvalidPriceException() {
        super("the suggested price can not be less than base price of the service! ");
    }
}
