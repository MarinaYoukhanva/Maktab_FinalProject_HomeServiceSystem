package com.freshome.entity.entityMapper;

import com.freshome.entity.Customer;
import com.freshome.entity.dto.CustomerCreateDTO;
import com.freshome.entity.dto.CustomerResponseDTO;

public class CustomerMapper {

    public static Customer customerFromDto(CustomerCreateDTO customerCreateDTO) {
        return Customer.builder()
                .firstname(customerCreateDTO.getFirstname())
                .lastname(customerCreateDTO.getLastname())
                .email(customerCreateDTO.getEmail())
                .password(customerCreateDTO.getPassword())
                .registerDateTime(customerCreateDTO.getRegisterDateTime())
                .status(customerCreateDTO.getStatus())
                .phoneNumber(customerCreateDTO.getPhoneNumber())
                .build();
    }

        public static CustomerResponseDTO dtoFromCustomer(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getRegisterDateTime(),
                customer.getStatus(),
                customer.getPhoneNumber()
        );
    }
//    public static CustomerResponseDTO from(Customer customer) {
//        return CustomerResponseDTO.builder()
//                .id(customer.getId())
//                .firstname(customer.getFirstname())
//                .lastname(customer.getLastname())
//                .email(customer.getEmail())
//                .password(customer.getPassword())
//                .registerDateTime(customer.getRegisterDateTime())
//                .status(customer.getStatus())
//                .phoneNumber(customer.getPhoneNumber())
//                .build();
//    }
}
