package com.freshome.service;

import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;
import com.freshome.dto.offer.OfferResponseWithExpertDTO;
import com.freshome.dto.offer.OfferUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface OfferService {

    OfferResponseDTO createOffer(@Valid OfferCreateDTO offerCreateDTO);

    OfferResponseDTO findOfferById(Long id);

    OfferResponseWithExpertDTO findOfferWithExpertById(Long id);

    List<OfferResponseDTO> findAllOffers();

    List<OfferResponseWithExpertDTO> findAllOffersWithExpert();

    List<OfferResponseWithExpertDTO> findOffersForOrder(Long id, String sortDirection);

    OfferResponseDTO updateOffer(@Valid OfferUpdateDTO updateDTO);

    void deleteOffer(Long id);
}
