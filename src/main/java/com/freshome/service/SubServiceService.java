package com.freshome.service;

import com.freshome.entity.SubService;
import com.freshome.dto.subService.SubServiceCreateDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.dto.subService.SubServiceUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface SubServiceService {

    SubServiceResponseDTO createSubService(@Valid SubServiceCreateDTO subServiceCreateDTO);

    SubServiceResponseDTO findSubServiceById(Long id);

    Optional<SubService> findOptionalSubServiceById(Long id);

    List<SubServiceResponseDTO> findAllSubServices();

    List<SubServiceResponseDTO> findAllByCategoryId(Long id);

    SubServiceResponseDTO updateSubService(@Valid SubServiceUpdateDTO updateDTO);

    void deleteSubService(Long id);
}
