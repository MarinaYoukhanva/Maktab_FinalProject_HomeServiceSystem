package com.freshome.web;

import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
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
}
