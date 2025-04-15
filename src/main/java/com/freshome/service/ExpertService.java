package com.freshome.service;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.expert.ExpertUpdateDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.entity.Expert;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ExpertService {

    ExpertResponseDTO createExpert(ExpertCreatDTO expertCreatDTO) throws IOException;

//    void calculateScore(Expert expert);

    ExpertResponseDTO findExpertById(Long id);

    Optional<Expert> findOptionalExpertById(Long id);

    List<Expert> findAll();

    List<ExpertResponseDTO> findAllExperts();

    void deleteExpertById(Long id);

    ExpertResponseDTO updateExpert(ExpertUpdateDTO updateDTO);

    List<ExpertResponseDTO> searchExpert(
            List<String> fields, List<String> values,
            String expertise, Double minScore, Double maxScore
    );

    void changePassword(Long expertId, ChangePasswordDTO dto);

    CreditResponseDTO findCreditForExpert(Long expertId);

    void addSubServiceForExpert(Long expertId, Long subServiceId);

    void removeSubServiceForExpert(Long expertId, Long subServiceId);

    List<SubServiceResponseDTO> findAllSubServicesOfExpert(Long expertId);
}
