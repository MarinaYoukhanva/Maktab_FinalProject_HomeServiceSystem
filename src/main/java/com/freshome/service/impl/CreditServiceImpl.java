package com.freshome.service.impl;

import com.freshome.entity.Credit;
import com.freshome.entity.Customer;
import com.freshome.entity.dto.credit.CreditCreateDTO;
import com.freshome.entity.dto.credit.CreditResponseDTO;
import com.freshome.entity.entityMapper.CreditMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.CreditRepository;
import com.freshome.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;

    @Override
    @Transactional
    public CreditResponseDTO createCredit(CreditCreateDTO creditCreateDTO) {
        return CreditMapper.dtoFromCredit(
                creditRepository.save(
                        CreditMapper.creditFromDto(creditCreateDTO))
        );
    }

    @Override
    public Credit createReturnCredit(CreditCreateDTO creditCreateDTO){
        return creditRepository.save(
                CreditMapper.creditFromDto(creditCreateDTO)
        );
    }

    @Override
    public CreditResponseDTO findCreditById(Long id) {
        return CreditMapper.dtoFromCredit(
                creditRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException(Credit.class, id))
        );
    }

    @Override
    public List<CreditResponseDTO> findAllCredits() {
        return creditRepository.findAll()
                .stream().map(CreditMapper::dtoFromCredit)
                .toList();
    }

    @Override
    public void deleteCredit(Long id) {
        if (!creditRepository.existsById(id))
            throw new NotFoundException(Customer.class, id);
        creditRepository.deleteById(id);
    }
}
