package com.freshome.entity.dto.customer;

import com.freshome.entity.Credit;
import com.freshome.entity.enumeration.UserStatus;
import java.time.LocalDateTime;


public record CustomerResponseDTO(

        Long id,
        String firstname,
        String lastname,
        String email,
        LocalDateTime registerDateTime,
        UserStatus status,
        String phoneNumber
//        Long creditId
) {
}
