package com.freshome.dto.order;

import java.time.LocalDateTime;

public record ReportSearchForExpertDTO(
        LocalDateTime registerDateFrom,
        LocalDateTime registerDateTo,
        Integer minOrderCount,
        Integer maxOrderCount,
        Integer minDoneOrderCount,
        Integer maxDoneOrderCount,
        Integer minOfferCount,
        Integer maxOfferCount
) {
}
