package com.freshome.service.impl;

import com.freshome.entity.Customer;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.CreditRepository;
import com.freshome.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;

    @Override
    public void deleteCredit(Long id) {
        if (!creditRepository.existsById(id))
            throw new NotFoundException(Customer.class, id);
        creditRepository.deleteById(id);
    }
}
