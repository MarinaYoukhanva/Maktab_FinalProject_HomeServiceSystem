package com.freshome.dto.customer;

import com.freshome.entity.enumeration.UserStatus;

import java.time.LocalDateTime;


public record CustomerResponseDTO(

        Long id,
        String firstname,
        String lastname,
        String username,
        String email,
        LocalDateTime registerDateTime,
        UserStatus status,
        String phoneNumber
) {
}
