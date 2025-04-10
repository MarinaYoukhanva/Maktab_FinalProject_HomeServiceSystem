package com.freshome.exception;

public class InvalidCaptchaException extends BusinessBadRequestException {
    public InvalidCaptchaException() {
        super("CAPTCHA is incorrect! ");
    }
}
