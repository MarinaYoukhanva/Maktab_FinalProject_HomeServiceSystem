package com.freshome.service;

import com.freshome.entity.Expert;
import com.freshome.entity.Review;
import com.freshome.dto.review.ReviewCreateDTO;
import com.freshome.dto.review.ReviewResponseDTO;
import com.freshome.dto.review.ReviewUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    ReviewResponseDTO createReview (ReviewCreateDTO reviewCreateDTO);

    void updateExpertScore(Expert expert);

    ReviewResponseDTO findReviewById(Long id);

    Optional<Review> findOptionalReviewById(Long id);

    List<ReviewResponseDTO> findAllReviews();

    ReviewResponseDTO updateReview(ReviewUpdateDTO updateDTO);

    void deleteReviewById(Long id);

    Double expertScoreFromRatingsAverage(Long expertId);
}
