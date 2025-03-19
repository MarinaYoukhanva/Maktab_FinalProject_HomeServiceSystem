package com.freshome.service;

import com.freshome.entity.Expert;
import com.freshome.entity.dto.expert.ExpertCreatDTO;
import com.freshome.entity.dto.expert.ExpertResponseDTO;
import com.freshome.entity.dto.expert.ExpertUpdateDTO;
import com.freshome.specification.Operator;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface ExpertService {

    ExpertResponseDTO createExpert(@Valid ExpertCreatDTO expertCreatDTO);

    ExpertResponseDTO findExpertById(Long id);

    Optional<Expert> findOptionalExpertById(Long id);

    List<ExpertResponseDTO> findAllExperts();

    void deleteExpertById(Long id);

    ExpertResponseDTO updateExpert(@Valid ExpertUpdateDTO expertUpdateDto);

    List<Expert> searchExpert(
            List<SingularAttribute<?, ?>> fields, List<Operator> operators, List<String> values,
            String expertise
    );
}
