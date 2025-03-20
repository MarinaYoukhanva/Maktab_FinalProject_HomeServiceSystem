package com.freshome.service;

import com.freshome.entity.ServiceCategory;
import com.freshome.dto.serviceCategory.ServiceCategoryCreateDTO;
import com.freshome.dto.serviceCategory.ServiceCategoryResponseDTO;
import com.freshome.dto.serviceCategory.ServiceCategoryUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface ServiceCategoryService {

    ServiceCategoryResponseDTO createServiceCategory(@Valid ServiceCategoryCreateDTO serviceCategoryCreateDTO);

    ServiceCategoryResponseDTO findServiceCategoryById(Long id);

    Optional<ServiceCategory> findOptionalServiceCategoryById(Long id);

    List<ServiceCategoryResponseDTO> findAllServiceCategory();

    ServiceCategoryResponseDTO updateServiceCategory(@Valid ServiceCategoryUpdateDTO updateDTO);

    void deleteServiceCategoryById(Long id);
}
