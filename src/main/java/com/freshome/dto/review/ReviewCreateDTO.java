package com.freshome.dto.review;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReviewCreateDTO(

        @NotNull(message = "rating can not be null! ")
        Double rating,

        String comment,

        @NotNull(message = "orderId can not be null! ")
        @Positive
        Long orderId

//        @NotNull(message = "customerId can not be null! ")
//        @Positive
//        Long customerId,
//
//        @NotNull(message = "expertId can not be null! ")
//        @Positive
//        Long expertId
) {
}
