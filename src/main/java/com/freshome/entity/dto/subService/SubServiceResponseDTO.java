package com.freshome.entity.dto.subService;

public record SubServiceResponseDTO(
        Long id,
        String name,
        Long basePrice,
        String description
) {
}
