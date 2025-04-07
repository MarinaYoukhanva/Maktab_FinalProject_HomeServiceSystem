package com.freshome.dto.order;

import com.freshome.entity.enumeration.OrderStatus;

import java.time.LocalDateTime;

public record OrderSearchDTO(
        LocalDateTime fromDate,
        LocalDateTime toDate,
        OrderStatus orderStatus,
        String serviceCategory,
        String subService
) {
}
