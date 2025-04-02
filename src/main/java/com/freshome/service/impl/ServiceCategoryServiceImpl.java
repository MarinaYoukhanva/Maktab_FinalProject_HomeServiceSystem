package com.freshome.service.impl;

import com.freshome.entity.ServiceCategory;
import com.freshome.dto.serviceCategory.ServiceCategoryCreateDTO;
import com.freshome.dto.serviceCategory.ServiceCategoryResponseDTO;
import com.freshome.dto.serviceCategory.ServiceCategoryUpdateDTO;
import com.freshome.entity.entityMapper.ServiceCategoryMapper;
import com.freshome.exception.ExistenceException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.ServiceCategoryRepository;
import com.freshome.service.ServiceCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;

    @Override
    @Transactional
    public ServiceCategoryResponseDTO createServiceCategory(ServiceCategoryCreateDTO createDTO) {
        if (serviceCategoryRepository.existsByName(createDTO.name()))
            throw new ExistenceException("ServiceCategoryName");
        return ServiceCategoryMapper.dtoFromServiceCategory(
                serviceCategoryRepository.save(
                        ServiceCategoryMapper.serviceCategoryFromDto(createDTO))
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
    public List<ServiceCategoryResponseDTO> findAllServiceCategory() {
        return serviceCategoryRepository.findAll()
                .stream().map(ServiceCategoryMapper::dtoFromServiceCategory)
                .toList();
    }

    @Override
    @Transactional
    public ServiceCategoryResponseDTO updateServiceCategory(ServiceCategoryUpdateDTO updateDTO) {
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(updateDTO.id())
                .orElseThrow(() -> new NotFoundException(ServiceCategory.class, updateDTO.id()));
        updateFields(serviceCategory, updateDTO);
        return ServiceCategoryMapper.dtoFromServiceCategory(
                serviceCategoryRepository.save(serviceCategory)
        );
    }

    @Override
    @Transactional
    public void deleteServiceCategoryById(Long id) {
        if (!serviceCategoryRepository.existsById(id))
            throw new NotFoundException(ServiceCategory.class, id);
        serviceCategoryRepository.deleteById(id);
    }

    private void updateFields(ServiceCategory serviceCategory,
                              ServiceCategoryUpdateDTO updateDTO) {
        if (StringUtils.hasText(updateDTO.name())) {
            if (serviceCategoryRepository.existsByName(updateDTO.name()))
                throw new ExistenceException("ServiceCategoryName");
            serviceCategory.setName(updateDTO.name());
        }

        if (StringUtils.hasText(updateDTO.description()))
            serviceCategory.setDescription(updateDTO.description());
    }
}
