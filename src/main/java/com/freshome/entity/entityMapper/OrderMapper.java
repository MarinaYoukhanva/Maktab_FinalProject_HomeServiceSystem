package com.freshome.entity.entityMapper;

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
}
