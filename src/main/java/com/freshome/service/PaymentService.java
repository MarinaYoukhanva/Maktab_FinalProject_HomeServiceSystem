package com.freshome.service;

import com.freshome.dto.payment.PaymentDTO;

public interface PaymentService {

    PaymentDTO getOrderPaymentInfo(Long orderId);

    Double payFromCredit(Long orderId);

    Double payFromCard(Long orderId);

    void validateCaptcha(String storedCaptcha, String captchaInput);
}
