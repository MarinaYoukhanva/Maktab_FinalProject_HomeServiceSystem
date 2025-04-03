package com.freshome.dto.expert;

import com.freshome.entity.Credit;
import com.freshome.entity.enumeration.UserStatus;

import java.time.LocalDateTime;

public record ExpertResponseDTO(

        Long id,
        String firstname,
        String lastname,
        String email,
        LocalDateTime registerDateTime,
        UserStatus status,
        String phoneNumber,
//        byte[] profileImage,
        Double score
//        Long creditId
) {
}
