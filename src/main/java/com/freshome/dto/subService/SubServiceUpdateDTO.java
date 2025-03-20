package com.freshome.dto.subService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record SubServiceUpdateDTO(
        @NotNull(message = "id can not be null for updating! ")
        @Positive
        Long id,
        String name,
        Long basePrice,
        String description
) {
}
