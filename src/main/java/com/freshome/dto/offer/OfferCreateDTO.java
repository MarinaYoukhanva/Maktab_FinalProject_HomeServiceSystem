package com.freshome.dto.offer;

import com.freshome.entity.Expert;
import com.freshome.entity.Order;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record OfferCreateDTO(

        @NotNull(message = "offerRegisterDateTime can not be null! ")
        LocalDateTime offerRegisterDateTime,

        @NotNull(message = "suggestedPriceByExpert can not be null! ")
        @PositiveOrZero
        Long suggestedPriceByExpert,

        @NotNull(message = "durationInHours can not be null! ")
        @PositiveOrZero
        Integer durationInHours,

        LocalDateTime startDateTime,

        @NotNull(message = "orderId can not be null! ")
        @Positive
        Long orderId,

        @NotNull(message = "expertId can not be null! ")
        @Positive
        Long expertId
) {
}
