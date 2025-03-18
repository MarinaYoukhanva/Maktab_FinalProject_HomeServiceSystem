package com.freshome.entity.entityMapper;

import com.freshome.entity.ServiceCategory;
import com.freshome.entity.dto.serviceCategory.ServiceCategoryCreateDTO;
import com.freshome.entity.dto.serviceCategory.ServiceCategoryResponseDTO;

public class ServiceCategoryMapper {

    public static ServiceCategory serviceCategoryFromDto(ServiceCategoryCreateDTO serviceCategoryCreateDTO) {
        return ServiceCategory.builder()
                .name(serviceCategoryCreateDTO.name())
                .description(serviceCategoryCreateDTO.description())
                .build();
    }

    public static ServiceCategoryResponseDTO dtoFromServiceCategory(ServiceCategory serviceCategory) {
        return new ServiceCategoryResponseDTO(
                serviceCategory.getId(),
                serviceCategory.getName(),
                serviceCategory.getDescription()
        );
    }
}
