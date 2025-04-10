package com.freshome.exception;

public class ChangePasswordException extends BusinessBadRequestException {
    public ChangePasswordException() {
        super("Changing password was not successful! ");
    }
}
