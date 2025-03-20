package com.freshome.entity.entityMapper;

import com.freshome.entity.Credit;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.credit.CreditResponseDTO;

public class CreditMapper {

    public static Credit creditFromDto(CreditCreateDTO creditCreateDTO) {
        return Credit.builder()
                .balance(creditCreateDTO.balance())
                .build();
    }

    public static CreditResponseDTO dtoFromCredit(Credit credit) {
        return new CreditResponseDTO(
                credit.getId(),
                credit.getBalance()
        );
    }
}
