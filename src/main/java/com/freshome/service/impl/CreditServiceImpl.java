package com.freshome.service.impl;

import com.freshome.dto.credit.CreditChargeOrWithdrawDTO;
import com.freshome.entity.Credit;
import com.freshome.entity.Customer;
import com.freshome.dto.credit.CreditCreateDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.credit.CreditUpdateDTO;
import com.freshome.entity.entityMapper.CreditMapper;
import com.freshome.exception.NotEnoughBalanceException;
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
    @Transactional
    public CreditResponseDTO updateCredit(CreditUpdateDTO updateDTO){
        Credit credit = creditRepository.findById(updateDTO.id())
                .orElseThrow(()-> new NotFoundException(Credit.class, updateDTO.id()));
        updateFields(credit, updateDTO);
        return CreditMapper.dtoFromCredit(
                creditRepository.save(credit)
        );
    }

    @Override
    public void deleteCredit(Long id) {
        if (!creditRepository.existsById(id))
            throw new NotFoundException(Customer.class, id);
        creditRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CreditResponseDTO chargeCredit(CreditChargeOrWithdrawDTO chargeDto){
        Credit credit = creditRepository.findById(chargeDto.id())
                .orElseThrow(()-> new NotFoundException(Credit.class, chargeDto.id()));
        credit.setBalance(credit.getBalance() + chargeDto.amount());
        return CreditMapper.dtoFromCredit(
                creditRepository.save(credit)
        );
    }

    @Override
    @Transactional
    public CreditResponseDTO withdrawFromCredit(CreditChargeOrWithdrawDTO withdrawDto){
        Credit credit = creditRepository.findById(withdrawDto.id())
                .orElseThrow(()-> new NotFoundException(Credit.class, withdrawDto.id()));
        if (credit.getBalance() < withdrawDto.amount())
            throw new NotEnoughBalanceException();
        credit.setBalance(credit.getBalance() - withdrawDto.amount());
        return CreditMapper.dtoFromCredit(
                creditRepository.save(credit)
        );
    }

    private void updateFields(Credit credit, CreditUpdateDTO updateDTO) {
        if (updateDTO.balance() != null)
            credit.setBalance(updateDTO.balance());
    }
}
