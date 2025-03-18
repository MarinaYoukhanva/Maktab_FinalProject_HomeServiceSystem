package com.freshome.entity.entityMapper;

import com.freshome.entity.Customer;
import com.freshome.entity.dto.customer.CustomerCreateDTO;
import com.freshome.entity.dto.customer.CustomerResponseDTO;
import com.freshome.entity.dto.customer.CustomerUpdateDTO;

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
//                .credit(customerCreateDTO.getCredit())
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
//                customer.getCredit() != null ? customer.getCredit().getId() : null
//                customer.getCredit().getId()
        );
    }

    public static Customer customerFromDto(Customer customer, CustomerUpdateDTO customerUpdateDto) {
        customer.setFirstname(customerUpdateDto.getFirstname());
        customer.setLastname(customerUpdateDto.getLastname());
        customer.setEmail(customerUpdateDto.getEmail());
        customer.setPassword(customerUpdateDto.getPassword());
        customer.setRegisterDateTime(customerUpdateDto.getRegisterDateTime());
        customer.setStatus(customerUpdateDto.getStatus());
        customer.setPhoneNumber(customerUpdateDto.getPhoneNumber());
        return customer;
    }
}
