package com.freshome.web;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.service.ExpertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/expert")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ExpertController {

    private final ExpertService expertService;

    @PostMapping(value = "/signup", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ExpertResponseDTO> signup(
            @Valid @ModelAttribute ExpertCreatDTO expertCreatDTO
            ) throws IOException {
        log.info("expert signup triggered ");
        return ResponseEntity.ok(
                expertService.createExpert(expertCreatDTO)
        );
    }

    @PutMapping("/update/add_sub_service")
    public ResponseEntity<Void> addSubServiceForExpert(
            @RequestParam(name = "expertId") Long expertId,
            @RequestParam(name = "subServiceId") Long subServiceId
    ){
        expertService.addSubServiceForExpert(expertId, subServiceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/sub_services/{expertId}")
    public ResponseEntity<List<SubServiceResponseDTO>> findSubServicesOfExpert(
            @PathVariable Long expertId
    ){
        return ResponseEntity.ok(
                expertService.findAllSubServicesOfExpert(expertId)
        );
    }

    @PutMapping("/update/change_password/{expertId}")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long expertId,
            @RequestBody @Valid ChangePasswordDTO passwordDto
    ) {
        expertService.changePassword(expertId, passwordDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExpertResponseDTO>> search(
            @RequestParam(required = false) List<String> fields,
            @RequestParam(required = false) List<String> values,
            @RequestParam(required = false) String expertise
    ){
        return ResponseEntity.ok(
                expertService.searchExpert(fields, values, expertise)
        );
    }
}
