package com.freshome.dto.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderCreateDTO(

        Long suggestedPriceByCustomer,

        @NotBlank(message = "description can not be null or blank! ")
        String description,


        @NotBlank(message = "city can not be null or blank! ")
        String city,

        @NotBlank(message = "street can not be null or blank! ")
        String street,


        @NotBlank(message = "plaque can not be null or blank! ")
        String plaque,

        @NotNull(message = "subServiceId can not be null! ")
        @Positive
        Long subServiceId
) {
}
