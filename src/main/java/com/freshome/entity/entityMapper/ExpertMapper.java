package com.freshome.entity.entityMapper;

import com.freshome.entity.Expert;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.entity.enumeration.UserStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDateTime;

public class ExpertMapper {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Expert expertFromDto(ExpertCreatDTO expertCreatDTO) throws IOException {
        return Expert.builder()
                .firstname(expertCreatDTO.getFirstname())
                .lastname(expertCreatDTO.getLastname())
                .email(expertCreatDTO.getEmail())
                .password(passwordEncoder.encode(expertCreatDTO.getPassword()))
//                .password(expertCreatDTO.getPassword())
                .registerDateTime(LocalDateTime.now())
                .status(UserStatus.NEW)
                .phoneNumber(expertCreatDTO.getPhoneNumber())
                .profileImage(expertCreatDTO.getProfileImage() != null ? expertCreatDTO.getProfileImage().getBytes() : null)
                .score(0.0)
//                .credit(expertCreatDTO.getCredit())
                .build();
    }

    public static ExpertResponseDTO dtoFromExpert(Expert expert) {
        return new ExpertResponseDTO(
                expert.getId(),
                expert.getFirstname(),
                expert.getLastname(),
                expert.getEmail(),
                expert.getRegisterDateTime(),
                expert.getStatus(),
                expert.getPhoneNumber(),
//                expert.getProfileImage(),
                expert.getScore()
//                expert.getCredit().getId()
        );
    }
}
