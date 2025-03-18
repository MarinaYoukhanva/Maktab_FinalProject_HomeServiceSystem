package com.freshome.service;

import com.freshome.entity.Order;
import com.freshome.entity.dto.order.OrderCreateDTO;
import com.freshome.entity.dto.order.OrderResponseDTO;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public interface OrderService {

    OrderResponseDTO createOrder(@Valid OrderCreateDTO orderCreateDTO);

    OrderResponseDTO findOrderById(Long id);

    Optional<Order> findOptionalOrderById(Long id);

    void deleteOrderById(Long id);
}
