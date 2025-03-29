package com.freshome.exception;

public class ExistenceException extends RuntimeException {
    public ExistenceException(String field) {
        super(field + " already exists! ");
    }
}
