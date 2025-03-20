package com.freshome.dto.subService;

public record SubServiceResponseDTO(
        Long id,
        String name,
        Long basePrice,
        String description
) {
}
