package com.freshome.service;

import com.freshome.entity.Review;
import com.freshome.entity.dto.review.ReviewCreateDTO;
import com.freshome.entity.dto.review.ReviewResponseDTO;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
public interface ReviewService {

    ReviewResponseDTO createReview (@Valid ReviewCreateDTO reviewCreateDTO);

    ReviewResponseDTO findReviewById(Long id);

    Optional<Review> findOptionalReviewById(Long id);

    void deleteReviewById(Long id);
}
