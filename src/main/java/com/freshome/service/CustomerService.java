package com.freshome.service;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerResponseDTO;
import com.freshome.dto.customer.CustomerUpdateDTO;
import com.freshome.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

//    CustomerResponseDTO createCustomer(@Valid CustomerCreateDTO customerCreateDTO);
    CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO);

    CustomerResponseDTO findCustomerById(Long id);

    Optional<Customer> findOptionalCustomerById(Long id);

    Optional<Customer> findByUsername(String username);

    List<Customer> findAll();

    List<CustomerResponseDTO> findAllCustomers();

    void deleteCustomer(Long id);

//    CustomerResponseDTO updateCustomer(@Valid CustomerUpdateDTO updateDTO);
    CustomerResponseDTO updateCustomer(CustomerUpdateDTO updateDTO);

    List<CustomerResponseDTO> searchCustomer(
            List<String> fields, List<String> values
    );

    void changePassword(ChangePasswordDTO dto, String username);

    CreditResponseDTO findCreditForCustomer(String username);

}
