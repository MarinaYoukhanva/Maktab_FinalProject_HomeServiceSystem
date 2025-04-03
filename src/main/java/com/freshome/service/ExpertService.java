package com.freshome.service;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.entity.Expert;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.expert.ExpertUpdateDTO;
import com.freshome.entity.SubService;
import com.freshome.specification.Operator;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Validated
public interface ExpertService {

    ExpertResponseDTO createExpert(@Valid ExpertCreatDTO expertCreatDTO) throws IOException;

    void calculateScore(Expert expert);

    ExpertResponseDTO findExpertById(Long id);

    Optional<Expert> findOptionalExpertById(Long id);

    List<ExpertResponseDTO> findAllExperts();

    void deleteExpertById(Long id);

    ExpertResponseDTO updateExpert(@Valid ExpertUpdateDTO updateDTO);

    List<ExpertResponseDTO> searchExpert(
            List<String> fields, List<String> values,
            String expertise
    );

    void changePassword(Long expertId, ChangePasswordDTO dto);

    CreditResponseDTO findCreditForExpert(Long expertId);

    void addSubServiceForExpert(Long expertId, Long subServiceId);

    List<SubServiceResponseDTO> findAllSubServicesOfExpert(Long expertId);
}
