package com.freshome.entity.entityMapper;

import com.freshome.dto.moderator.ModeratorCreateDTO;
import com.freshome.dto.moderator.ModeratorResponseDTO;
import com.freshome.entity.Moderator;
import com.freshome.entity.User;

import java.time.LocalDateTime;
import java.util.HashSet;

public class ModeratorMapper {

    public static Moderator moderatorFromDto(ModeratorCreateDTO createDTO) {
        User user = User.builder()
                .firstname(createDTO.getFirstname())
                .lastname(createDTO.getLastname())
                .username(createDTO.getUsername())
                .password(createDTO.getPassword())
                .email(createDTO.getEmail())
                .registerDateTime(LocalDateTime.now())
                .roles(new HashSet<>())
                .build();
        return Moderator.builder()
                .user(user)
                .build();
    }

    public static ModeratorResponseDTO dtoFromModerator(Moderator moderator) {
        return new ModeratorResponseDTO(
                moderator.getId(),
                moderator.getUser().getFirstname(),
                moderator.getUser().getLastname(),
                moderator.getUser().getUsername(),
                moderator.getUser().getEmail(),
                moderator.getUser().getRegisterDateTime()
        );
    }
}
