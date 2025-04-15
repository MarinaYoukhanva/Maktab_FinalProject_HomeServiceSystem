package com.freshome.dto.customer;

import com.freshome.entity.enumeration.UserStatus;

import java.time.LocalDateTime;

public record CustomerWithOrdersReportDTO(
        Long id,
        String firstname,
        String lastname,
        String username,
        String email,
        UserStatus status,
        String phoneNumber,
        LocalDateTime registerDateTime,
        int countPlacedOrders,
        int countDoneOrders
) {
}
