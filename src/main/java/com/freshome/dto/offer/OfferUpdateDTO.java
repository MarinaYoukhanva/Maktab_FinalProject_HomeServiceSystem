package com.freshome.dto.offer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OfferUpdateDTO(
        @NotNull(message = "id can not be null for updating! ")
        @Positive
        Long id,
        Long suggestedPriceByExpert,
        Integer durationInHours,
        LocalDateTime startDateTime
) {
}
