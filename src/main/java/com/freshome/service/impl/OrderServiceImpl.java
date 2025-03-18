package com.freshome.service.impl;

import com.freshome.entity.Customer;
import com.freshome.entity.Expert;
import com.freshome.entity.Order;
import com.freshome.entity.SubService;
import com.freshome.entity.dto.order.OrderCreateDTO;
import com.freshome.entity.dto.order.OrderResponseDTO;
import com.freshome.entity.entityMapper.OrderMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.OrderRepository;
import com.freshome.service.CustomerService;
import com.freshome.service.ExpertService;
import com.freshome.service.OrderService;
import com.freshome.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final SubServiceService subServiceService;

    @Transactional
    @Override
    public OrderResponseDTO createOrder(OrderCreateDTO orderCreateDTO){

        Order order = OrderMapper.orderFromDto(orderCreateDTO);
        Customer customer = customerService.findOptionalCustomerById(orderCreateDTO.customerId())
                .orElseThrow(() -> new NotFoundException(Customer.class, orderCreateDTO.customerId()));
        Expert expert = expertService.findOptionalExpertById(orderCreateDTO.expertId())
                .orElseThrow(() -> new NotFoundException(Expert.class, orderCreateDTO.expertId()));
        SubService subService = subServiceService.findOptionalSubServiceById(orderCreateDTO.subServiceId())
                .orElseThrow(() -> new NotFoundException(SubService.class, orderCreateDTO.subServiceId()));
        order.setCustomer(customer);
        order.setExpert(expert);
        order.setSubService(subService);

        return OrderMapper.dtoFromOrder(
                orderRepository.save(order)
        );
    }

    @Override
    public OrderResponseDTO findOrderById(Long id){
        return OrderMapper.dtoFromOrder(
                orderRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Order.class, id))
        );
    }

    @Override
    public Optional<Order> findOptionalOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteOrderById(Long id){
        if (!orderRepository.existsById(id))
            throw new NotFoundException(Order.class, id);
        orderRepository.deleteById(id);
    }
}
