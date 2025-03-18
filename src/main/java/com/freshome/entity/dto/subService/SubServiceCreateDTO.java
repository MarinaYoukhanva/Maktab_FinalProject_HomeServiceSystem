package com.freshome.entity.dto.subService;

import jakarta.persistence.ManyToOne;

public record SubServiceCreateDTO(
        String name,
        Long basePrice,
        String description,
        Long categoryId
) {
}
