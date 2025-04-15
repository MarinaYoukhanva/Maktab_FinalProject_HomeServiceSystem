package com.freshome.service.verification;

import com.freshome.entity.Customer;
import com.freshome.entity.User;
import com.freshome.entity.VerificationToken;
import com.freshome.entity.enumeration.UserStatus;
import com.freshome.exception.NotFoundException;
import com.freshome.exception.VerificationTokenAlreadyUsedException;
import com.freshome.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerVerificationService {

    private final CustomerRepository customerRepository;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;


    public void sendVerificationEmail (User user){
        VerificationToken token = verificationTokenService.createVerificationToken(user);

        String link = "http://localhost:8081/v1/customer/verify?token=" + token.getToken();
        emailService.sendSimpleEmail(
                user.getEmail(), "Verify Email", "Click to verify: " + link);
    }

    @Transactional
    public void verifyCustomer(String token) {
        VerificationToken verificationToken = verificationTokenService.findVerificationToken(token);
        if (verificationToken.isUsed())
            throw new VerificationTokenAlreadyUsedException();

        User user = verificationToken.getUser();
        Customer customer = customerRepository.findByUser_Id(user.getId())
                .orElseThrow(()-> new NotFoundException("Customer not found! "));
        customer.setStatus(UserStatus.APPROVED);
        customerRepository.save(customer);

        verificationTokenService.setTokenUsed(verificationToken);
    }
}
