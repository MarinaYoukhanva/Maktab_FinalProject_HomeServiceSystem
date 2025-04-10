package com.freshome.exception;

public class NotEnoughBalanceException extends BusinessBadRequestException {
    public NotEnoughBalanceException() {
        super("not enough balance for withdraw! ");
    }
}
