package com.freshome.entity.dto.credit;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreditCreateDTO(

        @PositiveOrZero
        Long balance
) {
}
