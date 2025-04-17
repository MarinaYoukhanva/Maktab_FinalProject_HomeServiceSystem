package com.freshome.service;

import com.freshome.dto.expert.ExpertWithOrdersReportDTO;
import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;
import com.freshome.dto.offer.OfferResponseWithExpertDTO;
import com.freshome.dto.offer.OfferUpdateDTO;
import com.freshome.dto.order.ReportSearchForExpertDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface OfferService {

    OfferResponseDTO createOffer(String username, OfferCreateDTO offerCreateDTO);

    OfferResponseDTO findOfferById(Long id);

    OfferResponseWithExpertDTO findOfferWithExpertById(Long id);

    List<OfferResponseDTO> findAllOffers();

    List<OfferResponseWithExpertDTO> findAllOffersWithExpert();

    List<OfferResponseWithExpertDTO> findOffersForOrder(Long id, String sortDirection);

    OfferResponseDTO updateOffer( OfferUpdateDTO updateDTO);

    ExpertWithOrdersReportDTO getExpertOrdersReport(Long expertId);

    List<ExpertWithOrdersReportDTO> getAllExpertsOrdersReports();

    List<ExpertWithOrdersReportDTO> filterAllExpertsOrdersReports(
            ReportSearchForExpertDTO searchDTO
    );

    void deleteOffer(Long id);
}
