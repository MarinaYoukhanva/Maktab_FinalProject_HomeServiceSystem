package com.freshome.web;

import com.freshome.dto.credit.CreditChargeOrWithdrawDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.service.CreditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/credit")
@RequiredArgsConstructor
@Validated
public class CreditController {

    private final CreditService creditService;

    @PutMapping("/update/charge")
    public ResponseEntity<CreditResponseDTO> chargeCredit(
            @RequestBody @Valid CreditChargeOrWithdrawDTO chargeDto
            ){
        return ResponseEntity.ok(
                creditService.chargeCredit(chargeDto));
    }

    @PutMapping("/update/withdraw")
    public ResponseEntity<CreditResponseDTO> withdrawCredit(
            @RequestBody @Valid CreditChargeOrWithdrawDTO chargeDto
    ){
        return ResponseEntity.ok(
                creditService.withdrawFromCredit(chargeDto)
        );
    }
}
