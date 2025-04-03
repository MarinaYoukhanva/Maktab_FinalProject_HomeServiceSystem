package com.freshome.entity.entityMapper;

import com.freshome.dto.offer.OfferResponseWithExpertDTO;
import com.freshome.entity.Offer;
import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;

import java.time.LocalDateTime;

public class OfferMapper {

    public static Offer offerFromDto(OfferCreateDTO offerCreateDTO) {
        return Offer.builder()
                .offerRegisterDateTime(LocalDateTime.now())
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
//                offer.getExpert().getId(),
        );
    }

    public static OfferResponseWithExpertDTO dtoWithExpertFromOffer(Offer offer){
        return new OfferResponseWithExpertDTO(
                offer.getId(),
                offer.getOfferRegisterDateTime(),
                offer.getSuggestedPriceByExpert(),
                offer.getDurationInHours(),
                offer.getStartDateTime(),
                offer.getExpert().getId(),
                offer.getExpert().getFirstname(),
                offer.getExpert().getLastname(),
                offer.getExpert().getScore()
        );
    }
}
