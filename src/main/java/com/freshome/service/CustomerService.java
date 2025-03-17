package com.freshome.service;

import com.freshome.entity.dto.CustomerCreateDTO;
import com.freshome.entity.dto.CustomerResponseDTO;
import com.freshome.entity.dto.CustomerUpdateDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CustomerService {

    CustomerResponseDTO createCustomer(@Valid CustomerCreateDTO customerCreateDTO);

    CustomerResponseDTO findCustomerById(Long id);

    List<CustomerResponseDTO> findAllCustomers();

    void deleteCustomer(Long id);

    CustomerResponseDTO updateCustomer(@Valid CustomerUpdateDto customerUpdateDto);
}
