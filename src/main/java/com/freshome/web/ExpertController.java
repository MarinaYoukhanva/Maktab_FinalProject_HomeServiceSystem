package com.freshome.web;

import com.freshome.dto.ChangePasswordDTO;
import com.freshome.dto.credit.CreditResponseDTO;
import com.freshome.dto.expert.ExpertCreatDTO;
import com.freshome.dto.expert.ExpertResponseDTO;
import com.freshome.dto.expert.ExpertUpdateDTO;
import com.freshome.dto.subService.SubServiceResponseDTO;
import com.freshome.service.ExpertService;
import com.freshome.service.verification.ExpertVerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/expert")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ExpertController {

    private final ExpertService expertService;
    private final ExpertVerificationService expertVerificationService;

    @PostMapping(value = "/signup", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ExpertResponseDTO> signup(
            @Valid @ModelAttribute ExpertCreatDTO expertCreatDTO
    ) throws IOException {
        log.info("expert signup triggered ");
        return ResponseEntity.ok(
                expertService.createExpert(expertCreatDTO)
        );
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyExpert(
            @RequestParam String token
    ) {
        expertVerificationService.verifyExpert(token);
        return ResponseEntity.ok(
                "Email verified successfully! "
        );
    }

    @PutMapping("/update")
    public ResponseEntity<ExpertResponseDTO> update(
            @Valid @RequestBody ExpertUpdateDTO expertUpdateDTO
    ) {
        log.info("expert update triggered ");
        return ResponseEntity.ok(
                expertService.updateExpert(expertUpdateDTO)
        );
    }

    @DeleteMapping("/delete/{expertId}")
    public ResponseEntity<ExpertResponseDTO> delete(
            @PathVariable Long expertId
    ){
        expertService.deleteExpertById(expertId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/add_sub_service")
    public ResponseEntity<Void> addSubServiceForExpert(
            Principal principal,
            @RequestParam(name = "subServiceId") Long subServiceId
    ) {
        expertService.addSubServiceForExpert(principal.getName(), subServiceId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/remove_sub_service")
    public ResponseEntity<Void> removeSubServiceForExpert(
            Principal principal,
            @RequestParam(name = "subServiceId") Long subServiceId
    ) {
        expertService.removeSubServiceForExpert(principal.getName(), subServiceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{expertId}")
    public ResponseEntity<ExpertResponseDTO> findExpert(
            @PathVariable Long expertId
    ) {
        return ResponseEntity.ok(
                expertService.findExpertById(expertId)
        );
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<ExpertResponseDTO>> findAllExperts() {
        return ResponseEntity.ok(
                expertService.findAllExperts()
        );
    }

    @GetMapping("/find/all/pending_approval")
    public ResponseEntity<List<ExpertResponseDTO>> findAllPendingApprovalExperts() {
        return ResponseEntity.ok(
                expertService.expertsPendingApproval()
        );
    }

    @PutMapping("/update/approve/{expertId}")
    public ResponseEntity<Void> approveExpert(
            @PathVariable Long expertId
    ) {
        expertService.approveExpert(expertId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/sub_services/{expertId}")
    public ResponseEntity<List<SubServiceResponseDTO>> findSubServicesOfExpert(
            @PathVariable Long expertId
    ) {
        return ResponseEntity.ok(
                expertService.findAllSubServicesOfExpert(expertId)
        );
    }

    @PutMapping("/update/change_password")
    public ResponseEntity<Void> changePassword(
            @RequestBody @Valid ChangePasswordDTO passwordDto,
            Principal principal
    ) {
        expertService.changePassword(principal.getName(), passwordDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ExpertResponseDTO>> search(
            @RequestParam(required = false) List<String> fields,
            @RequestParam(required = false) List<String> values,
            @RequestParam(required = false) String expertise,
            @RequestParam(required = false) Double minScore,
            @RequestParam(required = false) Double maxScore
    ) {
        return ResponseEntity.ok(
                expertService.searchExpert(fields, values, expertise, minScore, maxScore)
        );
    }

    @GetMapping("/find_credit")
    public ResponseEntity<CreditResponseDTO> findCredit(
            Principal principal
    ) {
        return ResponseEntity.ok(
                expertService.findCreditForExpert(principal.getName())
        );
    }
}
