package com.freshome.entity.entityMapper;

import com.freshome.entity.Expert;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ExpertMapper {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Expert expertFromDto(ExpertCreatDTO expertCreatDTO) {
        return Expert.builder()
                .firstname(expertCreatDTO.getFirstname())
                .lastname(expertCreatDTO.getLastname())
                .email(expertCreatDTO.getEmail())
                .password(passwordEncoder.encode(expertCreatDTO.getPassword()))
                .registerDateTime(expertCreatDTO.getRegisterDateTime())
                .status(expertCreatDTO.getStatus())
                .phoneNumber(expertCreatDTO.getPhoneNumber())
                .profileImage(expertCreatDTO.getProfileImage())
                .score(expertCreatDTO.getScore())
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
                expert.getProfileImage(),
                expert.getScore()
//                expert.getCredit().getId()
        );
    }
}
