package com.freshome.entity.entityMapper;

import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.expert.ExpertWithOrdersReportDTO;
import com.freshome.entity.Expert;
import com.freshome.entity.User;
import com.freshome.entity.enumeration.UserStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;

public class ExpertMapper {

    public static Expert expertFromDto(ExpertCreatDTO expertCreatDTO) throws IOException {

        User user = User.builder()
                .firstname(expertCreatDTO.getFirstname())
                .lastname(expertCreatDTO.getLastname())
                .username(expertCreatDTO.getUsername())
                .password(expertCreatDTO.getPassword())
                .email(expertCreatDTO.getEmail())
                .registerDateTime(LocalDateTime.now())
                .roles(new HashSet<>())
                .build();
        return Expert.builder()
                .phoneNumber(expertCreatDTO.getPhoneNumber())
                .status(UserStatus.NEW)
                .profileImage(expertCreatDTO
                        .getProfileImage() != null ? expertCreatDTO.getProfileImage().getBytes() : null)
                .score(0.0)
                .user(user)
                .build();
    }

    public static ExpertResponseDTO dtoFromExpert(Expert expert) {
        return new ExpertResponseDTO(
                expert.getId(),
                expert.getUser().getFirstname(),
                expert.getUser().getLastname(),
                expert.getUser().getUsername(),
                expert.getUser().getEmail(),
                expert.getUser().getRegisterDateTime(),
                expert.getStatus(),
                expert.getPhoneNumber(),
//                expert.getProfileImage(),
                expert.getScore()
        );
    }

    public static ExpertWithOrdersReportDTO reportDtoFromExpert (
            Expert expert,
            int countAllOrders,
            int countDoneOrders,
            int countOffers) {
        return new ExpertWithOrdersReportDTO(
                expert.getId(),
                expert.getUser().getFirstname(),
                expert.getUser().getLastname(),
                expert.getUser().getUsername(),
                expert.getUser().getEmail(),
                expert.getStatus(),
                expert.getPhoneNumber(),
                expert.getScore(),
                expert.getUser().getRegisterDateTime(),
                countAllOrders,
                countDoneOrders,
                countOffers
        );
    }
}
