package com.freshome.entity.entityMapper;

import com.freshome.dto.order.OrderHistoryDTO;
import com.freshome.dto.order.OrderResponseWithServiceDTO;
import com.freshome.entity.Offer;
import com.freshome.entity.Order;
import com.freshome.dto.order.OrderCreateDTO;
import com.freshome.dto.order.OrderResponseDTO;
import com.freshome.entity.embeddable.Address;
import com.freshome.entity.enumeration.OrderStatus;

import java.time.LocalDateTime;

public class OrderMapper {

    public static Order orderFromDto(OrderCreateDTO orderCreateDTO) {
        return Order.builder()
                .suggestedPriceByCustomer(orderCreateDTO.suggestedPriceByCustomer())
                .description(orderCreateDTO.description())
                .orderPlacementDateTime(LocalDateTime.now())
//                .orderExecutionDateTime(orderCreateDTO.orderExecutionDateTime())
                .address(new Address(
                        orderCreateDTO.city(), orderCreateDTO.street(),
                        orderCreateDTO.plaque()))
                .status(OrderStatus.WAITING_FOR_EXPERT_OFFERS)
                .build();
    }

    public static OrderResponseDTO dtoFromOrder(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getSuggestedPriceByCustomer(),
                order.getDescription(),
                order.getOrderPlacementDateTime(),
                order.getOrderExecutionDateTime(),
                order.getAddress(),
                order.getStatus()
//                order.getCustomer().getId(),
//                order.getExpert().getId(),
//                order.getSubService().getId()
        );
    }

    public static OrderResponseWithServiceDTO dtoWithServiceFromOrder(Order order) {
        return new OrderResponseWithServiceDTO(
                order.getId(),
                order.getSuggestedPriceByCustomer(),
                order.getDescription(),
                order.getOrderPlacementDateTime(),
                order.getOrderExecutionDateTime(),
                order.getAddress(),
                order.getStatus(),
                order.getSubService().getId(),
                order.getSubService().getName(),
                order.getSubService().getCategory().getId(),
                order.getSubService().getCategory().getName()
        );
    }

    public static OrderHistoryDTO historyFromOrder(Order order) {
        Offer selectedOffer = order.getOffers().stream().filter(
                o -> o.getExpert().getId().equals(order.getExpert().getId())
        ).findFirst().orElse(null);
        return new OrderHistoryDTO(
                order.getId(),
                order.getSuggestedPriceByCustomer(),
                order.getDescription(),
                order.getOrderPlacementDateTime(),
                order.getOrderExecutionDateTime(),
                order.getAddress(),
                order.getStatus(),

                selectedOffer != null ? selectedOffer.getId() : null,
                selectedOffer != null ? selectedOffer.getOfferRegisterDateTime() : null,
                selectedOffer != null ? selectedOffer.getSuggestedPriceByExpert() : null,
                selectedOffer != null ? selectedOffer.getDurationInHours() : null,
                selectedOffer != null ? selectedOffer.getStartDateTime() : null,

                order.getSubService().getId(),
                order.getSubService().getName(),

                order.getCustomer().getId(),
                order.getCustomer().getFirstname(),
                order.getCustomer().getLastname(),

                order.getExpert().getId(),
                order.getExpert().getFirstname(),
                order.getExpert().getLastname(),
                order.getExpert().getScore()
        );
    }
}
