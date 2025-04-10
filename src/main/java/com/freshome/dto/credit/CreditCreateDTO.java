package com.freshome.dto.credit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreditCreateDTO(

        @NotNull(message = "balance can not be null! ")
        @PositiveOrZero
        Double balance
) {
}
