package com.freshome.service;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.entity.Customer;
import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerResponseDTO;
import com.freshome.dto.customer.CustomerUpdateDTO;

import java.util.List;
import java.util.Optional;

//@Validated
public interface CustomerService {

//    CustomerResponseDTO createCustomer(@Valid CustomerCreateDTO customerCreateDTO);
    CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO);

    CustomerResponseDTO findCustomerById(Long id);

    Optional<Customer> findOptionalCustomerById(Long id);

    List<CustomerResponseDTO> findAllCustomers();

    void deleteCustomer(Long id);

//    CustomerResponseDTO updateCustomer(@Valid CustomerUpdateDTO updateDTO);
    CustomerResponseDTO updateCustomer(CustomerUpdateDTO updateDTO);

    List<CustomerResponseDTO> searchCustomer(
            List<String> fields, List<String> values
    );

    void changePassword(Long customerId, ChangePasswordDTO dto);

    CreditResponseDTO findCreditForCustomer(Long customerId);
}
