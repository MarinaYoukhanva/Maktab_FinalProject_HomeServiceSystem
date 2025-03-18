package com.freshome.entity.entityMapper;

import com.freshome.entity.Offer;
import com.freshome.entity.dto.offer.OfferCreateDTO;
import com.freshome.entity.dto.offer.OfferResponseDTO;

public class OfferMapper {

    public static Offer offerFromDto(OfferCreateDTO offerCreateDTO) {
        return Offer.builder()
                .offerRegisterDateTime(offerCreateDTO.offerRegisterDateTime())
                .suggestedPriceByExpert(offerCreateDTO.suggestedPriceByExpert())
                .durationInHours(offerCreateDTO.durationInHours())
                .startDateTime(offerCreateDTO.startDateTime())
//                .order(offerCreateDTO.order())
//                .expert(offerCreateDTO.expert())
                .build();
    }

    public static OfferResponseDTO dtoFromOffer(Offer offer) {
        return new OfferResponseDTO(
                offer.getId(),
                offer.getOfferRegisterDateTime(),
                offer.getSuggestedPriceByExpert(),
                offer.getDurationInHours(),
                offer.getStartDateTime()
//                offer.getOrder().getId(),
//                offer.getExpert().getId()
        );
    }
}
