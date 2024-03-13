package com.nhnacademy.shoppingmall.exception.review;

import com.nhnacademy.shoppingmall.exception.NotFoundException;

public class ReviewNotFoundException extends NotFoundException {
    public ReviewNotFoundException(Long reviewId) {
        super("Review not found: " + reviewId);
    }
}
