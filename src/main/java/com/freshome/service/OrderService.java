package com.freshome.service;

import com.freshome.dto.order.OrderSearchDTO;
import com.freshome.entity.Order;
import com.freshome.dto.order.OrderCreateDTO;
import com.freshome.dto.order.OrderResponseDTO;
import com.freshome.dto.order.OrderUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
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

    List<OrderResponseDTO> findAllByExpertId(Long expertId);

    List<Order> findAllOrdersBySubServiceIds(List<Long> subServiceIds);

    List<OrderResponseDTO> findAllBySubServiceIds(List<Long> subServiceIds);

    List<OrderResponseDTO> searchOrder(
            OrderSearchDTO searchDTO
    );

    OrderResponseDTO chooseExpertForOrder(Long orderId, Long expertId);

    OrderResponseDTO startOrder(Long orderId);

    OrderResponseDTO executeOrder(Long orderId);

    void deleteOrderById(Long id);
}
