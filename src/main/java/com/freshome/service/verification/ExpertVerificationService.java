package com.freshome.service.verification;

import com.freshome.entity.Customer;
import com.freshome.entity.Expert;
import com.freshome.entity.User;
import com.freshome.entity.VerificationToken;
import com.freshome.entity.enumeration.UserStatus;
import com.freshome.exception.NotFoundException;
import com.freshome.exception.VerificationTokenAlreadyUsedException;
import com.freshome.repository.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExpertVerificationService {

    private final ExpertRepository expertRepository;
    private final VerificationTokenService verificationTokenService;
    private final EmailService emailService;

    public void sendVerificationEmail (User user){
        VerificationToken token = verificationTokenService.createVerificationToken(user);

        String link = "http://localhost:8081/v1/expert/verify?token=" + token.getToken();
        emailService.sendSimpleEmail(
                user.getEmail(), "Verify Email", "Click to verify: " + link);
    }

    @Transactional
    public void verifyExpert(String token) {
        VerificationToken verificationToken = verificationTokenService.findVerificationToken(token);
        if (verificationToken.isUsed())
            throw new VerificationTokenAlreadyUsedException();

        User user = verificationToken.getUser();
        Expert expert = expertRepository.findByUser_Id(user.getId())
                .orElseThrow(()-> new NotFoundException("Expert not found! "));
        expert.setStatus(UserStatus.PENDING_APPROVAL);
        expertRepository.save(expert);

        verificationTokenService.setTokenUsed(verificationToken);
    }
}
