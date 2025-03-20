package com.freshome.entity.entityMapper;

import com.freshome.entity.SubService;
import com.freshome.dto.subService.SubServiceCreateDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;

public class SubServiceMapper {

    public static SubService subServiceFromDto(SubServiceCreateDTO serviceCreateDTO) {
        return SubService.builder()
                .name(serviceCreateDTO.name())
                .basePrice(serviceCreateDTO.basePrice())
                .description(serviceCreateDTO.description())
                .build();
    }

    public static SubServiceResponseDTO dtoFromSubService(SubService subService) {
        return new SubServiceResponseDTO(
                subService.getId(),
                subService.getName(),
                subService.getBasePrice(),
                subService.getDescription()
        );
    }
}
