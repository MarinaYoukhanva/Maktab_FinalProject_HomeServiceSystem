package com.freshome.dto.expert;

import com.freshome.entity.enumeration.UserStatus;

import java.time.LocalDateTime;

public record ExpertWithOrdersReportDTO(
        Long id,
        String firstname,
        String lastname,
        String username,
        String email,
        UserStatus status,
        String phoneNumber,
        Double score,
        LocalDateTime registerDateTime,
        int countAllOrders,
        int countDoneOrders,
        int countOffers
) {
}
