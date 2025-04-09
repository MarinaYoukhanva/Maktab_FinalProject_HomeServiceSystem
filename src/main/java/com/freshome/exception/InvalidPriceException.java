package com.freshome.exception;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException() {
        super("the suggested price can not be less than base price of the service! ");
    }
}
