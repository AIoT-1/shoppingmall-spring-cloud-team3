package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.ReviewDto;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.Review;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.exception.review.ReviewNotFoundException;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.repository.ReviewRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.ReviewService;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto.ProductReviewResponse> getReviews(Long productId) {
        return reviewRepository.findByProduct_Id(productId).stream()
                .map(ReviewDto.ProductReviewResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Long createReview(Long productId, ReviewDto.CreateRequest request) {
        Long userId = UserIdStore.getUserId();
        User user = userRepository.getReferenceById(userId);
        Product product = productRepository.getReferenceById(productId);
        Review savedReview = reviewRepository.save(request.toEntity(user, product));

        return savedReview.getId();
    }

    @Override
    public void updateReview(Long reviewId, ReviewDto.UpdateRequest request) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        review.updateReview(request.getRating(), request.getComment());
    }

    @Override
    public void deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new ReviewNotFoundException(reviewId);
        }
        reviewRepository.deleteById(reviewId);
    }


}
