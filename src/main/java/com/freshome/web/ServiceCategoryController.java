package com.freshome.web;

import com.freshome.dto.serviceCategory.ServiceCategoryCreateDTO;
import com.freshome.dto.serviceCategory.ServiceCategoryResponseDTO;
import com.freshome.service.ServiceCategoryService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/service_category")
@RequiredArgsConstructor
@Validated
public class ServiceCategoryController {

    private final ServiceCategoryService serviceCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ServiceCategoryResponseDTO> createServiceCategory(
            @Valid @RequestBody ServiceCategoryCreateDTO createDTO) {
        return ResponseEntity.ok(
                serviceCategoryService.createServiceCategory(createDTO)
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ServiceCategoryResponseDTO> findServiceCategoryById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                serviceCategoryService.findServiceCategoryById(id)
        );
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<ServiceCategoryResponseDTO>> findAll() {
        return ResponseEntity.ok(
                serviceCategoryService.findAllServiceCategory()
        );
    }


}
