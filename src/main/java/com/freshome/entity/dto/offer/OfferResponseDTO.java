package com.freshome.entity.dto.offer;

import com.freshome.entity.Expert;
import com.freshome.entity.Order;

import java.time.LocalDateTime;

public record OfferResponseDTO(
        Long id,
        LocalDateTime offerRegisterDateTime,
        Long suggestedPriceByExpert,
        Integer durationInHours,
        LocalDateTime startDateTime
//        Long orderId,
//        Long expertId
) {
}
