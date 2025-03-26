package com.freshome.dto.review;

public record ReviewResponseDTO(
        Long id,
        Double rating,
        String comment
) {
}
