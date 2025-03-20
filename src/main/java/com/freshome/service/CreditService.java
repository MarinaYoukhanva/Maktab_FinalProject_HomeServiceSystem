package com.freshome.service;

import com.freshome.entity.Credit;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.credit.CreditUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CreditService {

    CreditResponseDTO createCredit(@Valid CreditCreateDTO creditCreateDTO);

    Credit createReturnCredit(@Valid CreditCreateDTO creditCreateDTO);

    CreditResponseDTO findCreditById(Long id);

    List<CreditResponseDTO> findAllCredits();

    CreditResponseDTO updateCredit(@Valid CreditUpdateDTO updateDTO);

    void deleteCredit(Long id);
}
