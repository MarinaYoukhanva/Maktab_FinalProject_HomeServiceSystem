package com.freshome.exception;

public class NotPendingApprovalExpertException extends BusinessBadRequestException {
    public NotPendingApprovalExpertException() {
        super("the expert is already approved or the email hasn't been verified yet! ");
    }
}
