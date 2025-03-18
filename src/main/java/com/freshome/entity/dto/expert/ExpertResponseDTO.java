package com.freshome.entity.dto.expert;

import com.freshome.entity.Credit;
import com.freshome.entity.enumeration.UserStatus;

import java.time.LocalDateTime;

public record ExpertResponseDTO(

        Long id,
        String firstname,
        String lastname,
        String email,
        String password,
        LocalDateTime registerDateTime,
        UserStatus status,
        String phoneNumber,
        byte[] profileImage,
        Long score
//        Long creditId
) {
}
