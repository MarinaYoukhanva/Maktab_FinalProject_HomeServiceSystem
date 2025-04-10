package com.freshome.exception;

public class BusinessBadRequestException extends RuntimeException {
    public BusinessBadRequestException(String message) {
        super(message);
    }
}
