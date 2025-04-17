package com.freshome.web;

import com.freshome.dto.expert.ExpertWithOrdersReportDTO;
import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;
import com.freshome.dto.offer.OfferResponseWithExpertDTO;
import com.freshome.dto.order.ReportSearchForExpertDTO;
import com.freshome.service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/offer")
@RequiredArgsConstructor
@Validated
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/create")
    public ResponseEntity<OfferResponseDTO> createOffer(
            @Valid @RequestBody OfferCreateDTO createDTO,
            Principal principal
            ){
        return ResponseEntity.ok(
                offerService.createOffer(principal.getName(), createDTO));
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

    @GetMapping("/find/report_for_expert/{expertId}")
    public ResponseEntity<ExpertWithOrdersReportDTO> getExpertOrdersReport(
            @PathVariable Long expertId
    ){
        return ResponseEntity.ok(
                offerService.getExpertOrdersReport(expertId)
        );
    }

    @GetMapping("/find/all/reports")
    public ResponseEntity<List<ExpertWithOrdersReportDTO>> getAllExpertsOrdersReports(){
        return ResponseEntity.ok(
                offerService.getAllExpertsOrdersReports()
        );
    }

    @GetMapping("/find/all/filter_report")
    public ResponseEntity<List<ExpertWithOrdersReportDTO>> filterAllExpertsOrdersReports(
            @RequestBody ReportSearchForExpertDTO searchDto
            ){
        return ResponseEntity.ok(
                offerService.filterAllExpertsOrdersReports(searchDto)
        );
    }
}
