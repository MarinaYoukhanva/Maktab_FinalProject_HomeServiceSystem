package com.freshome.web;

import com.freshome.dto.moderator.ModeratorCreateDTO;
import com.freshome.dto.moderator.ModeratorResponseDTO;
import com.freshome.service.ExpertService;
import com.freshome.service.ModeratorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/moderator")
@RequiredArgsConstructor
@Validated
public class ModeratorController {

    private final ModeratorService moderatorService;
    private final ExpertService expertService;

    @PostMapping("/save")
    public ResponseEntity<ModeratorResponseDTO> create(
            @Valid @RequestBody ModeratorCreateDTO createDTO
            ){
        return ResponseEntity.ok(
                moderatorService.createModerator(createDTO)
        );
    }

    @PutMapping("/update/add_expert_for_sub_service")
    public ResponseEntity<Void> addSubServiceForExpert(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "subServiceId") Long subServiceId
    ) {
        expertService.addSubServiceForExpert(username, subServiceId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/remove_expert_from_sub_service")
    public ResponseEntity<Void> removeSubServiceForExpert(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "subServiceId") Long subServiceId
    ) {
        expertService.removeSubServiceForExpert(username, subServiceId);
        return ResponseEntity.noContent().build();
    }
}
