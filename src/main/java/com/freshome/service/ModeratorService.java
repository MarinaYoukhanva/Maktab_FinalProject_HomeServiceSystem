package com.freshome.service;

import com.freshome.dto.moderator.ModeratorCreateDTO;
import com.freshome.dto.moderator.ModeratorResponseDTO;

public interface ModeratorService {
    ModeratorResponseDTO createModerator (ModeratorCreateDTO createDTO);
}
