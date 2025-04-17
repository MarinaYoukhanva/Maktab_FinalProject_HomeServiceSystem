package com.freshome.service.impl;

import com.freshome.entity.Expert;
import com.freshome.entity.Order;
import com.freshome.entity.Review;
import com.freshome.dto.review.ReviewCreateDTO;
import com.freshome.dto.review.ReviewResponseDTO;
import com.freshome.dto.review.ReviewUpdateDTO;
import com.freshome.entity.entityMapper.ReviewMapper;
import com.freshome.entity.enumeration.OrderStatus;
import com.freshome.exception.NotCompletedOrderException;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.ReviewRepository;
import com.freshome.service.OrderService;
import com.freshome.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderService orderService;

    @Override
    @Transactional
    public ReviewResponseDTO createReview(ReviewCreateDTO reviewCreateDTO) {

        Order order = orderService.findOptionalOrderById(reviewCreateDTO.orderId())
                .orElseThrow(() -> new NotFoundException(Order.class, reviewCreateDTO.orderId()));
        if (order.getStatus() != OrderStatus.COMPLETED && order.getStatus() != OrderStatus.PAID)
            throw new NotCompletedOrderException();

        Review review = ReviewMapper.reviewFromDto(reviewCreateDTO);
        long delay = orderService.delayInHours(order.getId(), order.getExpert().getId());
        if (delay > 0)
            review.setRating(review.getRating() - (review.getRating() * 0.05 * delay));

        review.setOrder(order);
        reviewRepository.save(review);
        updateExpertScore(order.getExpert());

        return ReviewMapper.dtoFromReview(review);
    }

    @Override
    public void updateExpertScore(Expert expert) {
        Double score = reviewRepository.expertScoreFromRatingsAverage(expert.getId());
        expert.setScore(score == null ? 0.0 : score);
    }

    @Override
    public ReviewResponseDTO findReviewById(Long id) {
        return ReviewMapper.dtoFromReview(
                reviewRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(Review.class, id)));
    }

    @Override
    public Optional<Review> findOptionalReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<ReviewResponseDTO> findAllReviews() {
        return reviewRepository.findAll()
                .stream().map(ReviewMapper::dtoFromReview)
                .toList();
    }

    @Override
    @Transactional
    public ReviewResponseDTO updateReview(ReviewUpdateDTO updateDTO) {
        Review review = reviewRepository.findById(updateDTO.id())
                .orElseThrow(() -> new NotFoundException(Review.class, updateDTO.id()));
        updateFields(review, updateDTO);
        return ReviewMapper.dtoFromReview(
                reviewRepository.save(review)
        );
    }

    @Override
    @Transactional
    public void deleteReviewById(Long id) {
        if (!reviewRepository.existsById(id))
            throw new NotFoundException(Review.class, id);
        reviewRepository.deleteById(id);
    }

    @Override
    public Double expertScoreFromRatingsAverage(Long expertId) {
        Double score = reviewRepository.expertScoreFromRatingsAverage(expertId);
        return score == null ? 0.0 : score;
    }

    private void updateFields(Review review, ReviewUpdateDTO updateDTO) {
        if (StringUtils.hasText(updateDTO.comment()))
            review.setComment(updateDTO.comment());

        if (updateDTO.rating() != null)
            review.setRating(updateDTO.rating());
    }
}
