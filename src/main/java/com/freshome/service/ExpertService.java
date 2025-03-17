package com.freshome.service;

import com.freshome.entity.dto.ExpertCreatDTO;
import com.freshome.entity.dto.ExpertResponseDTO;

public interface ExpertService {

    ExpertResponseDTO createExpert(ExpertCreatDTO expertCreatDTO);
}
