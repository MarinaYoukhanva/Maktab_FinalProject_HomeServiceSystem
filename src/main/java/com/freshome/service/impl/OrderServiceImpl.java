package com.freshome.service.impl;

import com.freshome.dto.customer.CustomerWithOrdersReportDTO;
import com.freshome.dto.order.*;
import com.freshome.entity.Customer;
import com.freshome.entity.Expert;
import com.freshome.entity.Order;
import com.freshome.entity.SubService;
import com.freshome.entity.entityMapper.CustomerMapper;
import com.freshome.entity.entityMapper.OrderMapper;
import com.freshome.entity.enumeration.OrderStatus;
import com.freshome.exception.InvalidPriceException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.OrderRepository;
import com.freshome.service.CustomerService;
import com.freshome.service.ExpertService;
import com.freshome.service.OrderService;
import com.freshome.service.SubServiceService;
import com.freshome.specification.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
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
    public OrderResponseDTO createOrder(OrderCreateDTO orderCreateDTO) {

        Order order = OrderMapper.orderFromDto(orderCreateDTO);
        Customer customer = customerService.findOptionalCustomerById(orderCreateDTO.customerId())
                .orElseThrow(() -> new NotFoundException(Customer.class, orderCreateDTO.customerId()));
//        Expert expert = expertService.findOptionalExpertById(orderCreateDTO.expertId())
//                .orElseThrow(() -> new NotFoundException(Expert.class, orderCreateDTO.expertId()));
        SubService subService = subServiceService.findOptionalSubServiceById(orderCreateDTO.subServiceId())
                .orElseThrow(() -> new NotFoundException(SubService.class, orderCreateDTO.subServiceId()));
        if (orderCreateDTO.suggestedPriceByCustomer() < subService.getBasePrice())
            throw new InvalidPriceException();
        order.setCustomer(customer);
//        order.setExpert(expert);
        order.setSubService(subService);

        return OrderMapper.dtoFromOrder(
                orderRepository.save(order)
        );
    }

    @Override
    public OrderResponseDTO findOrderById(Long id) {
        return OrderMapper.dtoFromOrder(
                orderRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Order.class, id))
        );
    }

    @Override
    public Optional<Order> findOptionalOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(OrderUpdateDTO updateDTO) {
        Order order = orderRepository.findById(updateDTO.id())
                .orElseThrow(() -> new NotFoundException(Order.class, updateDTO.id()));
        updateFields(order, updateDTO);
        return OrderMapper.dtoFromOrder(
                orderRepository.save(order)
        );
    }

    @Override
    public List<OrderResponseDTO> findAllOrders() {
        return orderRepository.findAll()
                .stream().map(OrderMapper::dtoFromOrder)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findAllByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId)
                .stream().map(OrderMapper::dtoFromOrder)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findAllByExpertId(Long expertId) {
        return orderRepository.findByExpert_Id(expertId)
                .stream().map(OrderMapper::dtoFromOrder)
                .toList();
    }

    @Override
    public List<Order> findAllOrdersBySubServiceIds(List<Long> subServiceIds) {
        return orderRepository.findBySubService_IdIn(subServiceIds);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findAllBySubServiceIds(List<Long> subServiceIds) {
//        if (subServiceIds == null || subServiceIds.isEmpty())
//            throw new NotFoundException(" ");
        return orderRepository.findBySubService_IdIn(subServiceIds)
                .stream().map(OrderMapper::dtoFromOrder)
                .toList();
    }

    @Override
    public List<OrderResponseWithServiceDTO> searchOrder(
            OrderSearchDTO searchDTO
    ) {
        return orderRepository.findAll(
                        OrderSpecification.searchOrder(
                                searchDTO.fromDate(),
                                searchDTO.toDate(),
                                searchDTO.orderStatus(),
                                searchDTO.serviceCategory(),
                                searchDTO.subService())
                ).stream().map(OrderMapper::dtoWithServiceFromOrder)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDTO chooseExpertForOrder(Long orderId, Long expertId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(Order.class, orderId));
        Expert expert = expertService.findOptionalExpertById(expertId)
                .orElseThrow(() -> new NotFoundException(Expert.class, expertId));
        order.setStatus(OrderStatus.WAITING_FOR_EXPERT_ARRIVAL);
        order.setExpert(expert);
        return OrderMapper.dtoFromOrder(
                orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderResponseDTO startOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(Order.class, orderId));
        order.setStatus(OrderStatus.STARTED);
        return OrderMapper.dtoFromOrder(
                orderRepository.save(order)
        );
    }

    @Override
    @Transactional
    public OrderResponseDTO executeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(Order.class, orderId));
        order.setStatus(OrderStatus.COMPLETED);
        order.setOrderExecutionDateTime(LocalDateTime.now());
        return OrderMapper.dtoFromOrder(
                orderRepository.save(order)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderHistoryDTO> showOrderHistoryForExpert(Long expertId) {
        List<Order> orders = orderRepository.findDoneOrdersByExpertId(expertId);
        return orders
                .stream().map(OrderMapper::historyFromOrder)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderHistoryDTO> showOrderHistoryForCustomer(Long customerId) {
        return orderRepository.findDoneOrdersByCustomerId(customerId)
                .stream().map(OrderMapper::historyFromOrder)
                .toList();
    }


    @Override
    public int countOrdersByExpertId(Long expertId){
        return orderRepository.countOrderByExpert_Id(expertId);
    }

    @Override
    public int countDoneOrdersByExpertId(Long expertId){
        return orderRepository.countDoneOrderByExpertId(expertId);
    }

    @Override
    public CustomerWithOrdersReportDTO getCustomerOrdersReport(Long customerId){
        Customer customer = customerService.findOptionalCustomerById(customerId)
                .orElseThrow(() -> new NotFoundException(Customer.class, customerId));
        return CustomerMapper.reportDtoFromCustomer(
                customer,
                orderRepository.countOrderByCustomer_Id(customerId),
                orderRepository.countDoneOrderByCustomerId(customerId)
        );
    }

    @Override
    public List<CustomerWithOrdersReportDTO> getAllCustomersOrdersReports(){
        return customerService.findAll()
                .stream().map(
                        customer -> CustomerMapper.reportDtoFromCustomer(
                        customer,
                        orderRepository.countOrderByCustomer_Id(customer.getId()),
                        orderRepository.countDoneOrderByCustomerId(customer.getId())
                )).toList();
    }

    @Transactional
    @Override
    public void deleteOrderById(Long id) {
        if (!orderRepository.existsById(id))
            throw new NotFoundException(Order.class, id);
        orderRepository.deleteById(id);
    }

    private void updateFields(Order order, OrderUpdateDTO updateDTO) {
        if (updateDTO.suggestedPriceByCustomer() != null)
            order.setSuggestedPriceByCustomer(updateDTO.suggestedPriceByCustomer());

        if (StringUtils.hasText(updateDTO.description()))
            order.setDescription(updateDTO.description());

        if (updateDTO.orderExecutionDateTime() != null)
            order.setOrderExecutionDateTime(updateDTO.orderExecutionDateTime());

        if (StringUtils.hasText(updateDTO.street()))
            order.getAddress().setStreet(updateDTO.street());

//        if (StringUtils.hasText(updateDTO.avenue()))
//            order.getAddress().setAvenue(updateDTO.avenue());

        if (StringUtils.hasText(updateDTO.street()))
            order.getAddress().setPlaque(updateDTO.plaque());

        if (updateDTO.status() != null)
            order.setStatus(updateDTO.status());
    }
}
