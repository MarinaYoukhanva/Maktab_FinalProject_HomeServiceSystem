package com.freshome.service.impl;

import com.freshome.dto.expert.ExpertWithOrdersReportDTO;
import com.freshome.dto.offer.OfferResponseWithExpertDTO;
import com.freshome.entity.Expert;
import com.freshome.entity.Offer;
import com.freshome.entity.Order;
import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;
import com.freshome.dto.offer.OfferUpdateDTO;
import com.freshome.entity.SubService;
import com.freshome.entity.entityMapper.ExpertMapper;
import com.freshome.entity.entityMapper.OfferMapper;
import com.freshome.entity.enumeration.OrderStatus;
import com.freshome.entity.enumeration.UserStatus;
import com.freshome.exception.NotApprovedUserException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.OfferRepository;
import com.freshome.service.ExpertService;
import com.freshome.service.OfferService;
import com.freshome.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ExpertService expertService;
    private final OrderService orderService;

    @Override
    @Transactional
    public OfferResponseDTO createOffer(String username, OfferCreateDTO offerCreateDTO) {

        Offer offer = OfferMapper.offerFromDto(offerCreateDTO);
        Expert expert = expertService.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(Expert.class, username));
        if (expert.getStatus() != UserStatus.APPROVED)
            throw new NotApprovedUserException();
        Order order = orderService.findOptionalOrderById(offerCreateDTO.orderId())
                .orElseThrow(() -> new NotFoundException(Order.class, offerCreateDTO.orderId()));
        if (!expert.getSubServices().contains(order.getSubService()))
            throw new NotFoundException("expert does not have the service of the order!");
        order.setStatus(OrderStatus.WAITING_FOR_EXPERT_SELECTION);
        offer.setExpert(expert);
        offer.setOrder(order);

        return OfferMapper.dtoFromOffer(
                offerRepository.save(offer)
        );
    }

    @Override
    public OfferResponseDTO findOfferById(Long id) {
        return OfferMapper.dtoFromOffer(
                offerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Offer.class, id))
        );
    }

    @Override
    public OfferResponseWithExpertDTO findOfferWithExpertById(Long id) {
        return OfferMapper.dtoWithExpertFromOffer(
                offerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Offer.class, id)));
    }

    @Override
    public List<OfferResponseDTO> findAllOffers() {
        return offerRepository.findAll()
                .stream().map(OfferMapper::dtoFromOffer)
                .toList();
    }

    @Override
    public List<OfferResponseWithExpertDTO> findAllOffersWithExpert() {
        return offerRepository.findAll()
                .stream().map(
                        OfferMapper::dtoWithExpertFromOffer).toList();
    }

    @Override
    @Transactional
    public List<OfferResponseWithExpertDTO> findOffersForOrder(Long id, String sortDirection) {
        Sort.Direction direction = sortDirection
                .equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "suggestedPriceByExpert");
        List<Offer> offers = offerRepository.findByOrder_Id(id, sort);
        return offers.stream().map(
                OfferMapper::dtoWithExpertFromOffer).toList();
    }

    @Override
    @Transactional
    public OfferResponseDTO updateOffer(OfferUpdateDTO updateDTO) {
        Offer offer = offerRepository.findById(updateDTO.id())
                .orElseThrow(() -> new NotFoundException(Offer.class, updateDTO.id()));
        updateFields(offer, updateDTO);
        return OfferMapper.dtoFromOffer(
                offerRepository.save(offer)
        );
    }

    @Override
    public ExpertWithOrdersReportDTO getExpertOrdersReport(Long expertId) {
        Expert expert = expertService.findOptionalExpertById(expertId)
                .orElseThrow(() -> new NotFoundException(Expert.class, expertId));
        return ExpertMapper.reportDtoFromExpert(
                expert,
                orderService.countOrdersByExpertId(expertId),
                orderService.countDoneOrdersByExpertId(expertId),
                offerRepository.countByExpert_Id(expertId)
        );
    }

    @Override
    public List<ExpertWithOrdersReportDTO> getAllExpertsOrdersReports() {
        return expertService.findAll()
                .stream().map(
                        expert -> ExpertMapper.reportDtoFromExpert(
                                expert,
                                orderService.countOrdersByExpertId(expert.getId()),
                                orderService.countDoneOrdersByExpertId(expert.getId()),
                                offerRepository.countByExpert_Id(expert.getId()))
                ).toList();
    }

    @Override
    @Transactional
    public void deleteOffer(Long id) {
        if (!offerRepository.existsById(id))
            throw new NotFoundException(Offer.class, id);
        offerRepository.deleteById(id);
    }


    private void updateFields(Offer offer, OfferUpdateDTO updateDTO) {
        if (updateDTO.suggestedPriceByExpert() != null)
            offer.setSuggestedPriceByExpert(updateDTO.suggestedPriceByExpert());

        if (updateDTO.durationInHours() != null)
            offer.setDurationInHours(updateDTO.durationInHours());

        if (updateDTO.startDateTime() != null)
            offer.setStartDateTime(updateDTO.startDateTime());
    }
}
