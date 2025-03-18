package com.freshome.service;

import com.freshome.entity.SubService;
import com.freshome.entity.dto.subService.SubServiceCreateDTO;
import com.freshome.entity.dto.subService.SubServiceResponseDTO;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public interface SubServiceService {

    SubServiceResponseDTO createSubService(@Valid SubServiceCreateDTO subServiceCreateDTO);

    SubServiceResponseDTO findSubServiceById(Long id);

    Optional<SubService> findOptionalSubServiceById(Long id);

    void deleteSubService(Long id);
}
