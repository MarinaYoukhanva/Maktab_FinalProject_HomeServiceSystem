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

    Optional<Expert> findByUsername(String username);

    List<Expert> findAll();

    List<ExpertResponseDTO> findAllExperts();

    List<ExpertResponseDTO> expertsPendingApproval();

    void approveExpert(Long id);

    void deleteExpertById(Long id);

    ExpertResponseDTO updateExpert(ExpertUpdateDTO updateDTO);

    List<ExpertResponseDTO> searchExpert(
            List<String> fields, List<String> values,
            String expertise, Double minScore, Double maxScore
    );

    void changePassword(String username, ChangePasswordDTO dto);

    CreditResponseDTO findCreditForExpert(String username);

    void addSubServiceForExpert(String username, Long subServiceId);

    void removeSubServiceForExpert(String username, Long subServiceId);

    List<SubServiceResponseDTO> findAllSubServicesOfExpert(Long expertId);
}
