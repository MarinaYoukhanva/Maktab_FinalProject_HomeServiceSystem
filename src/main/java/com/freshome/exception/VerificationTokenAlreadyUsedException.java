package com.freshome.exception;

public class VerificationTokenAlreadyUsedException extends BusinessBadRequestException {
    public VerificationTokenAlreadyUsedException() {

        super("Your email is already verified! ");
    }
}
