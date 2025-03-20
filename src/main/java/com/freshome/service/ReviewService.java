package com.freshome.service;

import com.freshome.entity.Review;
import com.freshome.dto.review.ReviewCreateDTO;
import com.freshome.dto.review.ReviewResponseDTO;
import com.freshome.dto.review.ReviewUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public interface ReviewService {

    ReviewResponseDTO createReview (@Valid ReviewCreateDTO reviewCreateDTO);

    ReviewResponseDTO findReviewById(Long id);

    Optional<Review> findOptionalReviewById(Long id);

    ReviewResponseDTO updateReview(@Valid ReviewUpdateDTO updateDTO);

    void deleteReviewById(Long id);
}
