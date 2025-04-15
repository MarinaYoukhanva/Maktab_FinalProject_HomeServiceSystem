package com.freshome.service.verification;

import com.freshome.entity.User;
import com.freshome.entity.VerificationToken;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final TokenRepository tokenRepository;

    public VerificationToken createVerificationToken (User user) {

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setUsed(false);

        return tokenRepository.save(verificationToken);
    }

    public VerificationToken findVerificationToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(()-> new NotFoundException("Token not found!"));
    }

    public void setTokenUsed(VerificationToken token) {
        token.setUsed(true);
        tokenRepository.save(token);
    }
}
