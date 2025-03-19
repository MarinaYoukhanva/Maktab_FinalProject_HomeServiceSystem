package com.freshome.exception;

public class ChangePasswordException extends RuntimeException {
    public ChangePasswordException() {
        super("Changing password was not successful! ");
    }
}
