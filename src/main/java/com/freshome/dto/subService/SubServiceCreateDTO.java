package com.freshome.dto.subService;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SubServiceCreateDTO(

        @NotBlank(message = "name can not be null or blank! ")
        String name,

        @NotNull(message = "basePrice can not be null! ")
        Long basePrice,

        @NotBlank(message = "description can not be null or blank! ")
        String description,

        @NotNull(message = "categoryId can not be null! ")
        @Positive
        Long categoryId
) {
}
