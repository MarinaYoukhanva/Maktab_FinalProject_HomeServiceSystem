package com.freshome.service;

import com.freshome.entity.Order;
import com.freshome.dto.order.OrderCreateDTO;
import com.freshome.dto.order.OrderResponseDTO;
import com.freshome.dto.order.OrderUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface OrderService {

    OrderResponseDTO createOrder(@Valid OrderCreateDTO orderCreateDTO);

    OrderResponseDTO findOrderById(Long id);

    Optional<Order> findOptionalOrderById(Long id);

    List<OrderResponseDTO> findAllOrders();

    OrderResponseDTO updateOrder(@Valid OrderUpdateDTO updateDTO);

    List<OrderResponseDTO> findAllByCustomerId(Long customerId);

    List<Order> findAllOrdersBySubServiceIds(List<Long> subServiceIds);

    List<OrderResponseDTO> findAllBySubServiceIds(List<Long> subServiceIds);

    void deleteOrderById(Long id);
}
