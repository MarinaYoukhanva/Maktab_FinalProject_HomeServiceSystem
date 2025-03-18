package com.freshome.service.impl;

import com.freshome.entity.ServiceCategory;
import com.freshome.entity.SubService;
import com.freshome.entity.dto.subService.SubServiceCreateDTO;
import com.freshome.entity.dto.subService.SubServiceResponseDTO;
import com.freshome.entity.entityMapper.SubServiceMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.SubServiceRepository;
import com.freshome.service.ServiceCategoryService;
import com.freshome.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {

    private final SubServiceRepository subServiceRepository;
    private final ServiceCategoryService serviceCategoryService;

    @Override
    @Transactional
    public SubServiceResponseDTO createSubService(SubServiceCreateDTO subServiceCreateDTO){

        SubService subService = SubServiceMapper.subServiceFromDto(subServiceCreateDTO);
        ServiceCategory serviceCategory = serviceCategoryService
                .findOptionalServiceCategoryById(subServiceCreateDTO.categoryId())
                .orElseThrow(() -> new NotFoundException(ServiceCategory.class, subServiceCreateDTO.categoryId()));
        subService.setCategory(serviceCategory);

        return SubServiceMapper.dtoFromSubService(
                subServiceRepository.save(subService));

    }

    @Override
    public SubServiceResponseDTO findSubServiceById(Long id){
        return SubServiceMapper.dtoFromSubService(
                subServiceRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(SubService.class, id))
        );
    }

    @Override
    public Optional<SubService> findOptionalSubServiceById(Long id) {
        return subServiceRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteSubService(Long id){
        if (!subServiceRepository.existsById(id))
            throw new NotFoundException(SubService.class, id);
        subServiceRepository.deleteById(id);
    }
}
