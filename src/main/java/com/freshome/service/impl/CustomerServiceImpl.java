package com.freshome.service.impl;

import com.freshome.entity.Customer;
import com.freshome.entity.dto.CustomerCreateDTO;
import com.freshome.entity.dto.CustomerResponseDTO;
import com.freshome.entity.dto.CustomerUpdateDto;
import com.freshome.entity.entityMapper.CustomerMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.CustomerRepository;
import com.freshome.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
//@Validated
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
        return CustomerMapper.dtoFromCustomer(
                customerRepository.save(
                        CustomerMapper.customerFromDto(customerCreateDTO))
        );
    }

    @Override
    public CustomerResponseDTO findCustomerById(Long id) {
        return CustomerMapper.dtoFromCustomer(
                customerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Customer.class, id))
        );
    }

    @Override
    public List<CustomerResponseDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream().map(CustomerMapper::dtoFromCustomer)
                .toList();
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id))
            throw new NotFoundException(Customer.class, id);
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerUpdateDto customerUpdateDto) {
        Customer customer = customerRepository.findById(customerUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException(Customer.class, customerUpdateDto.getId()));
        return CustomerMapper.dtoFromCustomer(
                customerRepository.save(
                        CustomerMapper.customerFromDto(customer, customerUpdateDto))
        );
    }
}
