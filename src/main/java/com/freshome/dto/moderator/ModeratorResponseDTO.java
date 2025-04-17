package com.freshome.dto.moderator;

import java.time.LocalDateTime;

public record ModeratorResponseDTO(

        Long id,
        String firstname,
        String lastname,
        String username,
        String email,
        LocalDateTime registerDateTime
) {
}
