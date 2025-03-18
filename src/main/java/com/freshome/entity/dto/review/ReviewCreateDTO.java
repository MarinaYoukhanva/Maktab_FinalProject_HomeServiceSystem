package com.freshome.entity.dto.review;

import jakarta.persistence.ManyToOne;

public record ReviewCreateDTO(
        Integer rating,
        String comment,
        Long customerId,
        Long orderId,
        Long expertId
) {
}
