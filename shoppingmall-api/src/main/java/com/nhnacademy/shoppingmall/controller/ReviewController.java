package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.ReviewDto;
import com.nhnacademy.shoppingmall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products/{productId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    @GetMapping
    public ResponseEntity<List<ReviewDto.ProductReviewResponse>> getReviews(@PathVariable Long productId){
        return ResponseEntity.ok().body(reviewService.getReviews(productId));
    }
    @PostMapping
    public ResponseEntity<Long> createReview(@PathVariable Long productId, @RequestBody ReviewDto.CreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(productId, request));
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto.UpdateRequest request){
        reviewService.updateReview(reviewId, request);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
