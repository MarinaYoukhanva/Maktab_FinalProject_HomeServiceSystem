package com.freshome.service;

import com.freshome.entity.dto.CustomerCreateDTO;
import com.freshome.entity.dto.CustomerResponseDTO;
import com.freshome.entity.entityMapper.CustomerMapper;
import com.freshome.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO){
        return CustomerMapper.dtoFromCustomer(
                customerRepository.save(
                        CustomerMapper.customerFromDto(customerCreateDTO))
        );
    }
}
