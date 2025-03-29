package com.freshome.dto.serviceCategory;

import jakarta.validation.constraints.NotBlank;

public record ServiceCategoryCreateDTO(

        @NotBlank(message = "name can not be null or blank! ")
        String name,

        @NotBlank(message = "description can not be null or blank! ")
        String description
) {
}
