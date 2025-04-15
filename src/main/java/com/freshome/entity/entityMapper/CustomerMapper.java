package com.freshome.entity.entityMapper;

import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerResponseDTO;
import com.freshome.dto.customer.CustomerWithOrdersReportDTO;
import com.freshome.entity.Customer;
import com.freshome.entity.User;
import com.freshome.entity.enumeration.UserStatus;

import java.time.LocalDateTime;
import java.util.HashSet;

public class CustomerMapper {

    public static Customer customerFromDto(CustomerCreateDTO customerCreateDTO) {

        User user = User.builder()
                .firstname(customerCreateDTO.getFirstname())
                .lastname(customerCreateDTO.getLastname())
                .username(customerCreateDTO.getUsername())
                .password(customerCreateDTO.getPassword())
                .email(customerCreateDTO.getEmail())
                .registerDateTime(LocalDateTime.now())
                .roles(new HashSet<>())
                .build();
        return Customer.builder()
                .phoneNumber(customerCreateDTO.getPhoneNumber())
                .status(UserStatus.NEW)
                .user(user)
                .build();
    }

        public static CustomerResponseDTO dtoFromCustomer(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getUser().getFirstname(),
                customer.getUser().getLastname(),
                customer.getUser().getUsername(),
                customer.getUser().getEmail(),
                customer.getUser().getRegisterDateTime(),
                customer.getStatus(),
                customer.getPhoneNumber()
        );
    }

    public static CustomerWithOrdersReportDTO reportDtoFromCustomer(
            Customer customer,
            int countPlacedOrders,
            int countDoneOrders) {
        return new CustomerWithOrdersReportDTO(
                customer.getId(),
                customer.getUser().getFirstname(),
                customer.getUser().getLastname(),
                customer.getUser().getUsername(),
                customer.getUser().getEmail(),
                customer.getStatus(),
                customer.getPhoneNumber(),
                customer.getUser().getRegisterDateTime(),
                countPlacedOrders,
                countDoneOrders
        );
    }
}
