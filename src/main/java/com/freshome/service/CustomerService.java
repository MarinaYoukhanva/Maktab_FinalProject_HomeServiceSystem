package com.freshome.service;

import com.freshome.entity.dto.CustomerCreateDTO;
import com.freshome.entity.dto.CustomerResponseDTO;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerCreateDTO customerCreateDTO);
}
