package com.freshome.dto.review;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ReviewUpdateDTO(
        @NotNull(message = "id can not be null for updating! ")
        @Positive
        Long id,
        Integer rating,
        String comment
) {
}
