package com.freshome.service.impl;

import com.freshome.dto.offer.OfferResponseWithExpertDTO;
import com.freshome.entity.Expert;
import com.freshome.entity.Offer;
import com.freshome.entity.Order;
import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;
import com.freshome.dto.offer.OfferUpdateDTO;
import com.freshome.entity.entityMapper.OfferMapper;
import com.freshome.entity.enumeration.OrderStatus;
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
    public OfferResponseDTO createOffer(OfferCreateDTO offerCreateDTO) {

        Offer offer = OfferMapper.offerFromDto(offerCreateDTO);
        Expert expert = expertService.findOptionalExpertById(offerCreateDTO.expertId())
                .orElseThrow(()-> new NotFoundException(Expert.class, offerCreateDTO.expertId()));
        Order order = orderService.findOptionalOrderById(offerCreateDTO.orderId())
                .orElseThrow(()-> new NotFoundException(Order.class, offerCreateDTO.orderId()));
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
    public OfferResponseWithExpertDTO findOfferWithExpertById(Long id){
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Offer.class, id));
        expertService.calculateScore(offer.getExpert());
        return OfferMapper.dtoWithExpertFromOffer(offer);
    }

    @Override
    public List<OfferResponseDTO> findAllOffers() {
        return offerRepository.findAll()
                .stream().map(OfferMapper::dtoFromOffer)
                .toList();
    }

    @Override
    public List<OfferResponseWithExpertDTO> findAllOffersWithExpert(){
        List<Offer> offers = offerRepository.findAll();
        offers.forEach(
                offer-> expertService.calculateScore(offer.getExpert()));
        return offers.stream().map(
                OfferMapper::dtoWithExpertFromOffer).toList();
    }

    @Override
    @Transactional
    public List<OfferResponseWithExpertDTO> findOffersForOrder(Long id, String sortDirection){
        Sort.Direction direction = sortDirection
                .equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, "suggestedPriceByExpert");
        List<Offer> offers = offerRepository.findByOrder_Id(id, sort);
        offers.forEach(
                offer-> expertService.calculateScore(offer.getExpert()));
        return offers.stream().map(
                        OfferMapper::dtoWithExpertFromOffer).toList();
    }

    @Override
    @Transactional
    public OfferResponseDTO updateOffer(OfferUpdateDTO updateDTO) {
        Offer offer = offerRepository.findById(updateDTO.id())
                .orElseThrow(()-> new NotFoundException(Offer.class, updateDTO.id()));
        updateFields(offer, updateDTO);
        return OfferMapper.dtoFromOffer(
                offerRepository.save(offer)
        );
    }

    @Override
    @Transactional
    public void deleteOffer(Long id){
        if (!offerRepository.existsById(id))
            throw new NotFoundException(Offer.class, id);
        offerRepository.deleteById(id);
    }



    private void updateFields (Offer offer, OfferUpdateDTO updateDTO) {
        if (updateDTO.suggestedPriceByExpert() != null)
            offer.setSuggestedPriceByExpert(updateDTO.suggestedPriceByExpert());

        if (updateDTO.durationInHours() != null)
            offer.setDurationInHours(updateDTO.durationInHours());

        if(updateDTO.startDateTime() != null)
            offer.setStartDateTime(updateDTO.startDateTime());
    }
}
