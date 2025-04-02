package com.freshome.dto.order;

import com.freshome.entity.enumeration.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderUpdateDTO(
        @NotNull(message = "id can not be null for updating! ")
        @Positive
        Long id,
        Long suggestedPriceByCustomer,
        String description,
        LocalDateTime orderExecutionDateTime,
        String street,
        String avenue,
        String plaque,
        OrderStatus status
) {
}
