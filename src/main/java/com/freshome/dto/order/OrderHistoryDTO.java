package com.freshome.dto.order;

import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;

import java.time.LocalDateTime;

public record OrderHistoryDTO(
        Long orderId,
        Long suggestedPriceByCustomer,
        String description,
        LocalDateTime orderPlacementDateTime,
        LocalDateTime orderExecutionDateTime,
        Address address,
        OrderStatus status,

        Long offerId,
        LocalDateTime offerRegisterDateTime,
        Long suggestedPriceByExpert,
        Integer durationInHours,
        LocalDateTime startDateTime,

        Long subServiceId,
        String subServiceName,

        Long customerId,
        String customerFirstname,
        String customerLastname,

        Long expertId,
        String expertFirstname,
        String expertLastname,
        Double score
) {
}
