package com.freshome.exception;

public class NotApprovedUserException extends BusinessBadRequestException {
    public NotApprovedUserException() {
        super("User is not approved yet!");
    }
}
