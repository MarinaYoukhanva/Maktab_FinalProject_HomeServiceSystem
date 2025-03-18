package com.freshome.service.impl;

import com.freshome.entity.Credit;
import com.freshome.entity.Customer;
import com.freshome.entity.dto.credit.CreditCreateDTO;
import com.freshome.entity.dto.customer.CustomerCreateDTO;
import com.freshome.entity.dto.customer.CustomerResponseDTO;
import com.freshome.entity.dto.customer.CustomerUpdateDTO;
import com.freshome.entity.entityMapper.CustomerMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.CustomerRepository;
import com.freshome.service.CreditService;
import com.freshome.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Transactional
//@Validated
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CreditService creditService;

    @Override
    @Transactional
    public CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO) {

        Customer customer = CustomerMapper.customerFromDto(customerCreateDTO);
        Credit credit = creditService.createReturnCredit(new CreditCreateDTO(0L));
        customer.setCredit(credit);

        return CustomerMapper.dtoFromCustomer(
                customerRepository.save(customer)
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
    public Optional<Customer> findOptionalCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<CustomerResponseDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream().map(CustomerMapper::dtoFromCustomer)
                .toList();
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id))
            throw new NotFoundException(Customer.class, id);
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CustomerResponseDTO updateCustomer(CustomerUpdateDTO customerUpdateDto) {
        Customer customer = customerRepository.findById(customerUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException(Customer.class, customerUpdateDto.getId()));
        return CustomerMapper.dtoFromCustomer(
                customerRepository.save(
                        CustomerMapper.customerFromDto(customer, customerUpdateDto))
        );
    }
}
