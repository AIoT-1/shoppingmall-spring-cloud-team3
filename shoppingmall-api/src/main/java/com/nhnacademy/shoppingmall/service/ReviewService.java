package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto.ProductReviewResponse> getReviews(Long productId);

    Long createReview(Long productId, ReviewDto.CreateRequest request);

    void updateReview(Long reviewId, ReviewDto.UpdateRequest request);

    void deleteReview(Long reviewId);
}
