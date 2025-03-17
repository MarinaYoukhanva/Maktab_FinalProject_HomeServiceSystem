package com.freshome.entity.entityMapper;

import com.freshome.entity.Expert;
import com.freshome.entity.dto.ExpertCreatDTO;
import com.freshome.entity.dto.ExpertResponseDTO;

public class ExpertMapper {

    public static Expert expertFromDto(ExpertCreatDTO expertCreatDTO) {
        return Expert.builder()
                .firstname(expertCreatDTO.getFirstname())
                .lastname(expertCreatDTO.getLastname())
                .email(expertCreatDTO.getEmail())
                .password(expertCreatDTO.getPassword())
                .registerDateTime(expertCreatDTO.getRegisterDateTime())
                .status(expertCreatDTO.getStatus())
                .phoneNumber(expertCreatDTO.getPhoneNumber())
                .profileImage(expertCreatDTO.getProfileImage())
                .score(expertCreatDTO.getScore())
                .build();
    }

    public static ExpertResponseDTO dtoFromExpert(Expert expert) {
        return new ExpertResponseDTO(
                expert.getId(),
                expert.getFirstname(),
                expert.getLastname(),
                expert.getEmail(),
                expert.getPassword(),
                expert.getRegisterDateTime(),
                expert.getStatus(),
                expert.getPhoneNumber(),
                expert.getProfileImage(),
                expert.getScore()
        );
    }
}
