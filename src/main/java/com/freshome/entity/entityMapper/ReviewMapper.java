package com.freshome.entity.entityMapper;

import com.freshome.entity.Review;
import com.freshome.entity.dto.review.ReviewCreateDTO;
import com.freshome.entity.dto.review.ReviewResponseDTO;

public class ReviewMapper {

    public static Review reviewFromDto(ReviewCreateDTO reviewCreateDTO){
        return Review.builder()
                .rating(reviewCreateDTO.rating())
                .comment(reviewCreateDTO.comment())
                .build();
    }

    public static ReviewResponseDTO dtoFromReview(Review review){
        return new ReviewResponseDTO(
                review.getId(),
                review.getRating(),
                review.getComment()
        );
    }
}
