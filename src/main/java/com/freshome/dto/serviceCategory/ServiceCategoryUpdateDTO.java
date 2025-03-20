package com.freshome.dto.serviceCategory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ServiceCategoryUpdateDTO(
        @NotNull(message = "id can not be null for updating! ")
        @Positive
        Long id,
        String name,
        String description
) {
}
