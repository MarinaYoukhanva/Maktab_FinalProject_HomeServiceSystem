package com.freshome.service;

import com.freshome.dto.customer.CustomerWithOrdersReportDTO;
import com.freshome.dto.order.*;
import com.freshome.entity.Order;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderResponseDTO createOrder(String username, OrderCreateDTO orderCreateDTO);

    OrderResponseDTO findOrderById(Long id);

    Optional<Order> findOptionalOrderById(Long id);

    List<OrderResponseDTO> findAllOrders();

    OrderResponseDTO updateOrder(OrderUpdateDTO updateDTO);

    List<OrderResponseDTO> findAllByCustomerId(Long customerId);

    List<OrderResponseDTO> findAllByExpertId(Long expertId);

    List<Order> findAllOrdersBySubServiceIds(List<Long> subServiceIds);

    List<OrderResponseDTO> findAllBySubServiceIds(List<Long> subServiceIds);

    List<OrderResponseWithServiceDTO> searchOrder(
            OrderSearchDTO searchDTO
    );

    OrderResponseDTO chooseExpertForOrder(Long orderId, Long expertId);

    OrderResponseDTO startOrder(Long orderId);

    OrderResponseDTO executeOrder(Long orderId);

    long delayInHours(Long orderId, Long expertId);

    List<OrderHistoryDTO> showOrderHistoryForExpert(Long expertId);

    List<OrderHistoryDTO> showOrderHistoryForCustomer(Long customerId);


    int countOrdersByExpertId(Long expertId);

    int countDoneOrdersByExpertId(Long expertId);

    CustomerWithOrdersReportDTO getCustomerOrdersReport(Long customerId);

    List<CustomerWithOrdersReportDTO> getAllCustomersOrdersReports();

    List<CustomerWithOrdersReportDTO> filterAllCustomersOrdersReports(
            ReportSearchDTO searchDTO
    );

    void deleteOrderById(Long id);
}
