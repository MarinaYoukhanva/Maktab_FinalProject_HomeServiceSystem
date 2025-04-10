package com.freshome.dto.payment;

import com.freshome.entity.Order;

public record PaymentDTO(
        Double paymentAmount,
        Order order
) {
}
