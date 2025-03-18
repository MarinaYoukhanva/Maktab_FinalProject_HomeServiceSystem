package com.freshome.service;

import com.freshome.entity.ServiceCategory;
import com.freshome.entity.dto.serviceCategory.ServiceCategoryCreateDTO;
import com.freshome.entity.dto.serviceCategory.ServiceCategoryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public interface ServiceCategoryService {

    ServiceCategoryResponseDTO createServiceCategory(@Valid ServiceCategoryCreateDTO serviceCategoryCreateDTO);

    ServiceCategoryResponseDTO findServiceCategoryById(Long id);

    Optional<ServiceCategory> findOptionalServiceCategoryById(Long id);

    void deleteServiceCategoryById(Long id);
}
