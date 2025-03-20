package com.freshome.dto.order;

import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;

import java.time.LocalDateTime;

public record OrderCreateDTO(
        Long suggestedPriceByCustomer,
        String description,
        LocalDateTime orderPlacementDateTime,
        LocalDateTime orderExecutionDateTime,
        Address address,
        OrderStatus status,
        Long customerId,
        Long expertId,
        Long subServiceId
) {
}
