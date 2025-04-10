package com.freshome.dto.credit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CreditChargeOrWithdrawDTO(

        @NotNull(message = "id can not be null for updating! ")
        @Positive
        Long id,

        @PositiveOrZero
        Double amount
) {
}
