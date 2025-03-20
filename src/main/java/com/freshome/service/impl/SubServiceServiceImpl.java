package com.freshome.service.impl;

import com.freshome.entity.ServiceCategory;
import com.freshome.entity.SubService;
import com.freshome.dto.subService.SubServiceCreateDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.dto.subService.SubServiceUpdateDTO;
import com.freshome.entity.entityMapper.SubServiceMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.SubServiceRepository;
import com.freshome.service.ServiceCategoryService;
import com.freshome.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {

    private final SubServiceRepository subServiceRepository;
    private final ServiceCategoryService serviceCategoryService;

    @Override
    @Transactional
    public SubServiceResponseDTO createSubService(SubServiceCreateDTO subServiceCreateDTO) {

        SubService subService = SubServiceMapper.subServiceFromDto(subServiceCreateDTO);
        ServiceCategory serviceCategory = serviceCategoryService
                .findOptionalServiceCategoryById(subServiceCreateDTO.categoryId())
                .orElseThrow(() -> new NotFoundException(ServiceCategory.class, subServiceCreateDTO.categoryId()));
        subService.setCategory(serviceCategory);

        return SubServiceMapper.dtoFromSubService(
                subServiceRepository.save(subService));

    }

    @Override
    public SubServiceResponseDTO findSubServiceById(Long id) {
        return SubServiceMapper.dtoFromSubService(
                subServiceRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(SubService.class, id))
        );
    }

    @Override
    public Optional<SubService> findOptionalSubServiceById(Long id) {
        return subServiceRepository.findById(id);
    }

    @Override
    @Transactional
    public SubServiceResponseDTO updateSubService(SubServiceUpdateDTO updateDTO) {
        SubService subService = subServiceRepository.findById(updateDTO.id())
                .orElseThrow(() -> new NotFoundException(SubService.class, updateDTO.id()));
        updateFields(subService, updateDTO);
        return SubServiceMapper.dtoFromSubService(
                subServiceRepository.save(subService)
        );
    }

    @Transactional
    @Override
    public void deleteSubService(Long id) {
        if (!subServiceRepository.existsById(id))
            throw new NotFoundException(SubService.class, id);
        subServiceRepository.deleteById(id);
    }

    private void updateFields(SubService subService, SubServiceUpdateDTO updateDTO) {
        if (StringUtils.hasText(updateDTO.name()))
            subService.setName(updateDTO.name());

        if (updateDTO.basePrice() != null)
            subService.setBasePrice(updateDTO.basePrice());

        if (StringUtils.hasText(updateDTO.description()))
            subService.setDescription(updateDTO.description());
    }
}
