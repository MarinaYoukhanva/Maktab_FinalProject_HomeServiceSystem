package com.freshome.entity.entityMapper;

import com.freshome.entity.Customer;
import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomerMapper {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Customer customerFromDto(CustomerCreateDTO customerCreateDTO) {
        return Customer.builder()
                .firstname(customerCreateDTO.getFirstname())
                .lastname(customerCreateDTO.getLastname())
                .email(customerCreateDTO.getEmail())
                .password(passwordEncoder.encode(customerCreateDTO.getPassword()))
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
                customer.getRegisterDateTime(),
                customer.getStatus(),
                customer.getPhoneNumber()
//                customer.getCredit() != null ? customer.getCredit().getId() : null
//                customer.getCredit().getId()
        );
    }
}
