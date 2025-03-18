package com.freshome.service;

import com.freshome.entity.Customer;
import com.freshome.entity.dto.customer.CustomerCreateDTO;
import com.freshome.entity.dto.customer.CustomerResponseDTO;
import com.freshome.entity.dto.customer.CustomerUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface CustomerService {

    CustomerResponseDTO createCustomer(@Valid CustomerCreateDTO customerCreateDTO);

    CustomerResponseDTO findCustomerById(Long id);

    Optional<Customer> findOptionalCustomerById(Long id);

    List<CustomerResponseDTO> findAllCustomers();

    void deleteCustomer(Long id);

    CustomerResponseDTO updateCustomer(@Valid CustomerUpdateDTO customerUpdateDto);
}
