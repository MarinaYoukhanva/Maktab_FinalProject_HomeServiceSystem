package com.freshome.service;

import com.freshome.entity.Customer;
import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerResponseDTO;
import com.freshome.dto.customer.CustomerUpdateDTO;
import com.freshome.specification.Operator;
import jakarta.persistence.metamodel.SingularAttribute;
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

    CustomerResponseDTO updateCustomer(@Valid CustomerUpdateDTO updateDTO);

    List<CustomerResponseDTO> searchCustomer(
            List<SingularAttribute<?, ?>> fields, List<Operator> operators, List<String> values
    );

    void changePassword(Long customerId, String oldPassword, String newPassword);
}
