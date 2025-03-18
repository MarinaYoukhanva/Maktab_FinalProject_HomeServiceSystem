package com.freshome.entity.entityMapper;

import com.freshome.entity.Order;
import com.freshome.entity.dto.order.OrderCreateDTO;
import com.freshome.entity.dto.order.OrderResponseDTO;

public class OrderMapper {

    public static Order orderFromDto(OrderCreateDTO orderCreateDTO) {
        return Order.builder()
                .suggestedPriceByCustomer(orderCreateDTO.suggestedPriceByCustomer())
                .description(orderCreateDTO.description())
                .orderPlacementDateTime(orderCreateDTO.orderPlacementDateTime())
                .orderExecutionDateTime(orderCreateDTO.orderExecutionDateTime())
                .address(orderCreateDTO.address())
                .status(orderCreateDTO.status())
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
