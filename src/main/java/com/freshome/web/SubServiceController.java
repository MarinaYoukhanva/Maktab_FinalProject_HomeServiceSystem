package com.freshome.web;

import com.freshome.dto.subService.SubServiceCreateDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.service.SubServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/sub_service")
@RequiredArgsConstructor
@Validated
public class SubServiceController {

    private final SubServiceService subServiceService;

    @PostMapping("/create")
    public ResponseEntity<SubServiceResponseDTO> createSubService (
            @Valid @RequestBody SubServiceCreateDTO createDTO
            ){
        return ResponseEntity.ok(
                subServiceService.createSubService(createDTO)
        );
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SubServiceResponseDTO> findSubServiceById (
            @PathVariable Long id
    ){
        return ResponseEntity.ok(
                subServiceService.findSubServiceById(id)
        );
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<SubServiceResponseDTO>> findAllSubService (){
        return ResponseEntity.ok(
                subServiceService.findAllSubServices()
        );
    }

    @GetMapping("/find/all/{categoryId}")
    public ResponseEntity<List<SubServiceResponseDTO>> findAllSubServiceByCategoryId (
            @PathVariable Long categoryId
    ){
        return ResponseEntity.ok(
                subServiceService.findAllByCategoryId(categoryId)
        );
    }
}
