package com.freshome.dto.credit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record CreditUpdateDTO(

        @NotNull(message = "id can not be null for updating! ")
        @Positive
        Long id,

        @PositiveOrZero
        Double balance
) {
}
