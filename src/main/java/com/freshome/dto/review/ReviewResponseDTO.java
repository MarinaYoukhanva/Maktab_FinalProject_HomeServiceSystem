package com.freshome.dto.review;

public record ReviewResponseDTO(
        Long id,
        Integer rating,
        String comment
) {
}
