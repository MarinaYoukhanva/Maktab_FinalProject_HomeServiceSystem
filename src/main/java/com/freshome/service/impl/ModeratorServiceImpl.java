package com.freshome.service.impl;

import com.freshome.dto.moderator.ModeratorCreateDTO;
import com.freshome.dto.moderator.ModeratorResponseDTO;
import com.freshome.entity.Moderator;
import com.freshome.entity.Role;
import com.freshome.entity.entityMapper.ModeratorMapper;
import com.freshome.repository.ModeratorRepository;
import com.freshome.service.ModeratorService;
import com.freshome.service.RoleService;
import com.freshome.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModeratorServiceImpl implements ModeratorService {

    private final ModeratorRepository moderatorRepository;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    @Transactional
    public ModeratorResponseDTO createModerator(ModeratorCreateDTO createDTO) {
        Role role = roleService.findByName(Role.ADMIN);
        Moderator moderator = ModeratorMapper.moderatorFromDto(createDTO);
        moderator.getUser().getRoles().add(role);

        userService.save(moderator.getUser());
        return ModeratorMapper.dtoFromModerator(
                moderatorRepository.save(moderator)
        );
    }
}
