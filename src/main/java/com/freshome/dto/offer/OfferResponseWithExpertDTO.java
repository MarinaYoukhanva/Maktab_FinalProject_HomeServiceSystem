package com.freshome.dto.offer;

import java.time.LocalDateTime;

public record OfferResponseWithExpertDTO(
        Long id,
        LocalDateTime offerRegisterDateTime,
        Long suggestedPriceByExpert,
        Integer durationInHours,
        LocalDateTime startDateTime,

        Long expertId,
        String firstname,
        String lastname,
        Double score
) {
}