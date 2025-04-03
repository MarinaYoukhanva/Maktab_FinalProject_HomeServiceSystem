package com.freshome.web;

import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;
import com.freshome.dto.offer.OfferResponseWithExpertDTO;
import com.freshome.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/offer")
@RequiredArgsConstructor
@Validated
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/create")
    public ResponseEntity<OfferResponseDTO> createOffer(
            @Valid @RequestBody OfferCreateDTO createDTO
            ){
        return ResponseEntity.ok(
                offerService.createOffer(createDTO));
    }

    @GetMapping("/find/{offerId}")
    public ResponseEntity<OfferResponseDTO> findOfferById(
            @PathVariable Long offerId
    ){
        return ResponseEntity.ok(
                offerService.findOfferById(offerId)
        );
    }

    @GetMapping("/find_with_expert/{offerId}")
    public ResponseEntity<OfferResponseWithExpertDTO> findOfferWithExpertById(
            @PathVariable Long offerId
    ){
        return ResponseEntity.ok(
                offerService.findOfferWithExpertById(offerId)
        );
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<OfferResponseDTO>> findAll() {
        return ResponseEntity.ok(
                offerService.findAllOffers()
        );
    }

    @GetMapping("/find/all_with_expert")
    public ResponseEntity<List<OfferResponseWithExpertDTO>> findAllWithExpert() {
        return ResponseEntity.ok(
                offerService.findAllOffersWithExpert()
        );
    }

    @GetMapping("/find/all/{orderId}")
    public ResponseEntity<List<OfferResponseWithExpertDTO>> findOffersForOrder(
            @PathVariable Long orderId,
            @RequestParam(defaultValue = "desc", required = false) String sortDirection
    ){
        return ResponseEntity.ok(
                offerService.findOffersForOrder(orderId,  sortDirection)
        );
    }
}
