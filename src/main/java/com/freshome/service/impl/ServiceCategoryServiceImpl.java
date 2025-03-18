package com.freshome.service.impl;

import com.freshome.entity.ServiceCategory;
import com.freshome.entity.dto.serviceCategory.ServiceCategoryCreateDTO;
import com.freshome.entity.dto.serviceCategory.ServiceCategoryResponseDTO;
import com.freshome.entity.entityMapper.ServiceCategoryMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.ServiceCategoryRepository;
import com.freshome.service.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;

    @Override
    @Transactional
    public ServiceCategoryResponseDTO createServiceCategory(ServiceCategoryCreateDTO serviceCategoryCreateDTO) {
        return ServiceCategoryMapper.dtoFromServiceCategory(
                serviceCategoryRepository.save(
                        ServiceCategoryMapper.serviceCategoryFromDto(serviceCategoryCreateDTO))
        );
    }

    @Override
    public ServiceCategoryResponseDTO findServiceCategoryById(Long id) {
        return ServiceCategoryMapper.dtoFromServiceCategory(
                serviceCategoryRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(ServiceCategory.class, id))
        );
    }

    @Override
    public Optional<ServiceCategory> findOptionalServiceCategoryById(Long id) {
        return serviceCategoryRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteServiceCategoryById(Long id) {
        if (!serviceCategoryRepository.existsById(id))
            throw new NotFoundException(ServiceCategory.class, id);
        serviceCategoryRepository.deleteById(id);
    }
}
