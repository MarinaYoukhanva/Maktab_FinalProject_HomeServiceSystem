package com.freshome.service;

import com.freshome.entity.dto.expert.ExpertCreatDTO;
import com.freshome.entity.dto.expert.ExpertResponseDTO;
import com.freshome.entity.dto.expert.ExpertUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ExpertService {

    ExpertResponseDTO createExpert(@Valid ExpertCreatDTO expertCreatDTO);

    ExpertResponseDTO findExpertById(Long id);

    List<ExpertResponseDTO> findAllExperts();

    void deleteExpert(Long id);

    ExpertResponseDTO updateExpert(@Valid ExpertUpdateDTO expertUpdateDto);
}
