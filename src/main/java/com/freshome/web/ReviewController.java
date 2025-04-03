package com.freshome.web;

import com.freshome.dto.review.ReviewCreateDTO;
import com.freshome.dto.review.ReviewResponseDTO;
import com.freshome.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/review")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<ReviewResponseDTO> createReview(
            @Valid @RequestBody ReviewCreateDTO createDTO
            ){
        return ResponseEntity.ok(
                reviewService.createReview(createDTO));
    }
}
