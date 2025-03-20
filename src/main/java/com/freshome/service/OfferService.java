package com.freshome.service;

import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;
import com.freshome.dto.offer.OfferUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OfferService {

    OfferResponseDTO createOffer(@Valid OfferCreateDTO offerCreateDTO);

    OfferResponseDTO findOfferById(Long id);

    OfferResponseDTO updateOffer(@Valid OfferUpdateDTO updateDTO);

    void deleteOffer(Long id);
}
