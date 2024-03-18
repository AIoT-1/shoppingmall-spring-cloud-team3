package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.Review;
import com.nhnacademy.shoppingmall.enitiy.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDto {

    @Getter
    public static class ProductReviewResponse {
        private Long id;
        private Integer rating;
        private String comment;
        private String userName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;


        public static ProductReviewResponse fromEntity(Review review) {
            ProductReviewResponse response = new ProductReviewResponse();
            response.id = review.getId();
            response.rating = review.getRating();
            response.comment = review.getComment();
            response.userName = review.getUser().getName();
            response.createdAt = review.getCreatedAt();
            response.updatedAt = review.getUpdatedAt();
            return response;
        }
    }

    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CreateRequest {
        private Integer rating;
        private String comment;
        public Review toEntity(User user, Product product) {

            return Review.builder()
                    .user(user)
                    .product(product)
                    .rating(rating)
                    .comment(comment)
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateRequest {
        private Integer rating;
        private String comment;

    }
}
