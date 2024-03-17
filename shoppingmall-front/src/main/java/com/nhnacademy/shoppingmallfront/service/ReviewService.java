package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getReviews(int productId);
    void addReview(int rating, String comment, int productId);
    void deleteReview(int reviewId, int productId);
    void updateReview(int rating, String comment, int reviewId, int productId);
}