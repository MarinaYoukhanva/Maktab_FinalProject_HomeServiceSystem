package com.freshome.service.impl;

import com.freshome.entity.Expert;
import com.freshome.entity.Offer;
import com.freshome.entity.Order;
import com.freshome.dto.offer.OfferCreateDTO;
import com.freshome.dto.offer.OfferResponseDTO;
import com.freshome.dto.offer.OfferUpdateDTO;
import com.freshome.entity.entityMapper.OfferMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.OfferRepository;
import com.freshome.service.ExpertService;
import com.freshome.service.OfferService;
import com.freshome.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
