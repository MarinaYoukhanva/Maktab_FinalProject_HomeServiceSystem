package com.freshome.dto.offer;

import com.freshome.entity.Expert;
import com.freshome.entity.Order;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record OfferCreateDTO(

        @NotNull(message = "suggestedPriceByExpert can not be null! ")
        @PositiveOrZero
        Long suggestedPriceByExpert,

        @NotNull(message = "durationInHours can not be null! ")
        @PositiveOrZero
        Integer durationInHours,

        @NotNull(message = "startDateTime can not be null! ")
        @FutureOrPresent
        LocalDateTime startDateTime,

        @NotNull(message = "orderId can not be null! ")
        @Positive
        Long orderId
) {
}
