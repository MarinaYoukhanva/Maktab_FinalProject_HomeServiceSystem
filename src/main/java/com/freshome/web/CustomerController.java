package com.freshome.web;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.customer.CustomerCreateDTO;
import com.freshome.dto.customer.CustomerResponseDTO;
import com.freshome.dto.customer.CustomerUpdateDTO;
import com.freshome.entity.User;
import com.freshome.service.CustomerService;
import com.freshome.service.verification.CustomerVerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerVerificationService customerVerificationService;

    @PostMapping("/signup")
    public ResponseEntity<CustomerResponseDTO> signup(
            @Valid @RequestBody CustomerCreateDTO customerCreateDTO) {
        return ResponseEntity.ok(
                customerService.createCustomer(customerCreateDTO)
        );
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyCustomer(
            @RequestParam String token
    ){
        customerVerificationService.verifyCustomer(token);
        return ResponseEntity.ok(
                "Email verified successfully! "
        );
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<CustomerResponseDTO> findCustomerById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                customerService.findCustomerById(id)
        );
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CustomerResponseDTO>> findAllCustomers() {
        return ResponseEntity.ok(
                customerService.findAllCustomers()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @Valid @RequestBody CustomerUpdateDTO customerUpdateDTO
    ) {
        return ResponseEntity.ok(
                customerService.updateCustomer(customerUpdateDTO)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponseDTO>> searchCustomer(
            @RequestParam(required = false) List<String> fields,
            @RequestParam(required = false) List<String> values
    ) {
        return ResponseEntity.ok(
                customerService.searchCustomer(fields, values)
        );
    }

    @PutMapping("/update/change_password/{customerId}")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long customerId,
            @RequestBody @Valid ChangePasswordDTO passwordDto,
            @AuthenticationPrincipal User user
    ) {
        customerService.changePassword(customerId, passwordDto, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find_credit/{customerId}")
    public ResponseEntity<CreditResponseDTO> findCredit(
            @PathVariable Long customerId
    ){
        return ResponseEntity.ok(
                customerService.findCreditForCustomer(customerId)
        );
    }
}
