package com.freshome.entity.entityMapper;

import com.freshome.entity.Expert;
import com.freshome.entity.dto.expert.ExpertCreatDTO;
import com.freshome.entity.dto.expert.ExpertResponseDTO;
import com.freshome.entity.dto.expert.ExpertUpdateDTO;

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
//                .credit(expertCreatDTO.getCredit())
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
//                expert.getCredit().getId()
        );
    }

    public static Expert expertFromDto(Expert expert, ExpertUpdateDTO expertUpdateDTO) {
        expert.setFirstname(expertUpdateDTO.getFirstname());
        expert.setLastname(expertUpdateDTO.getLastname());
        expert.setEmail(expertUpdateDTO.getEmail());
        expert.setPassword(expertUpdateDTO.getPassword());
        expert.setRegisterDateTime(expertUpdateDTO.getRegisterDateTime());
        expert.setStatus(expertUpdateDTO.getStatus());
        expert.setPhoneNumber(expertUpdateDTO.getPhoneNumber());
        expert.setProfileImage(expertUpdateDTO.getProfileImage());
        expert.setScore(expertUpdateDTO.getScore());
        return expert;
    }
}
