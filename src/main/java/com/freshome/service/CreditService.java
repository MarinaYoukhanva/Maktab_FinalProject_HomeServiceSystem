package com.freshome.service;

import com.freshome.entity.Credit;
import com.freshome.entity.dto.credit.CreditCreateDTO;
import com.freshome.entity.dto.credit.CreditResponseDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CreditService {

    CreditResponseDTO createCredit(@Valid CreditCreateDTO creditCreateDTO);

    Credit createReturnCredit(@Valid CreditCreateDTO creditCreateDTO);

    CreditResponseDTO findCreditById(Long id);

    List<CreditResponseDTO> findAllCredits();

    void deleteCredit(Long id);
}
