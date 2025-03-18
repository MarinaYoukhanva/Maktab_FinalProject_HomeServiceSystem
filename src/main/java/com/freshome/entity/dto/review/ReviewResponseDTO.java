package com.freshome.entity.dto.review;

public record ReviewResponseDTO(
        Long id,
        Integer rating,
        String comment
) {
}
