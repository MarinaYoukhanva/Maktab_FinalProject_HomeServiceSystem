package com.freshome.dto.order;

import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record OrderCreateDTO(

        Long suggestedPriceByCustomer,

        @NotBlank(message = "description can not be null or blank! ")
        String description,

        @NotNull(message = "description can not be null! ")
        LocalDateTime orderPlacementDateTime,

        LocalDateTime orderExecutionDateTime,

        @NotBlank(message = "city can not be null or blank! ")
        String city,

        @NotBlank(message = "street can not be null or blank! ")
        String street,

        @NotBlank(message = "avenue can not be null or blank! ")
        String avenue,

        @NotNull(message = "plaque can not be null! ")
        Integer plaque,

        @NotNull(message = "status can not be null! ")
        OrderStatus status,

        @NotNull(message = "customerId can not be null! ")
        @Positive
        Long customerId,

        @NotNull(message = "expertId can not be null! ")
        @Positive
        Long expertId,

        @NotNull(message = "subServiceId can not be null! ")
        @Positive
        Long subServiceId
) {
}
