package com.freshome.service;

import com.freshome.entity.dto.offer.OfferCreateDTO;
import com.freshome.entity.dto.offer.OfferResponseDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OfferService {

    OfferResponseDTO createOffer(@Valid OfferCreateDTO offerCreateDTO);

    OfferResponseDTO findOfferById(Long id);

    void deleteOffer(Long id);
}
