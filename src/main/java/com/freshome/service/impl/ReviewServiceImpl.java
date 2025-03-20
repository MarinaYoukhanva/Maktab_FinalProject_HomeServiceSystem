package com.freshome.service.impl;

import com.freshome.entity.Customer;
import com.freshome.entity.Expert;
import com.freshome.entity.Order;
import com.freshome.entity.Review;
import com.freshome.dto.review.ReviewCreateDTO;
import com.freshome.dto.review.ReviewResponseDTO;
import com.freshome.dto.review.ReviewUpdateDTO;
import com.freshome.entity.entityMapper.ReviewMapper;
import com.freshome.exception.NotFoundException;
import com.freshome.repository.ReviewRepository;
import com.freshome.service.CustomerService;
import com.freshome.service.ExpertService;
import com.freshome.service.OrderService;
import com.freshome.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CustomerService customerService;
    private final OrderService orderService;
    private final ExpertService expertService;

    @Override
    @Transactional
    public ReviewResponseDTO createReview(ReviewCreateDTO reviewCreateDTO) {

        Review review = ReviewMapper.reviewFromDto(reviewCreateDTO);
        Customer customer = customerService.findOptionalCustomerById(reviewCreateDTO.customerId())
                .orElseThrow(()-> new NotFoundException(Customer.class, reviewCreateDTO.customerId()));
        Order order = orderService.findOptionalOrderById(reviewCreateDTO.orderId())
                .orElseThrow(()-> new NotFoundException(Order.class, reviewCreateDTO.orderId()));
        Expert expert = expertService.findOptionalExpertById(reviewCreateDTO.expertId())
                .orElseThrow(()-> new NotFoundException(Expert.class, reviewCreateDTO.expertId()));
        review.setCustomer(customer);
        review.setOrder(order);
        review.setExpert(expert);

        return ReviewMapper.dtoFromReview(
                reviewRepository.save(review));
    }

    @Override
    public ReviewResponseDTO findReviewById(Long id) {
        return ReviewMapper.dtoFromReview(
                reviewRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(Review.class, id)));
    }

    @Override
    public Optional<Review> findOptionalReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    @Transactional
    public ReviewResponseDTO updateReview(ReviewUpdateDTO updateDTO) {
        Review review = reviewRepository.findById(updateDTO.id())
                .orElseThrow(()-> new NotFoundException(Review.class, updateDTO.id()));
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

    private void updateFields(Review review, ReviewUpdateDTO updateDTO) {
        if (StringUtils.hasText(updateDTO.comment()))
            review.setComment(updateDTO.comment());

        if (updateDTO.rating() != null)
            review.setRating(updateDTO.rating());
    }
}
