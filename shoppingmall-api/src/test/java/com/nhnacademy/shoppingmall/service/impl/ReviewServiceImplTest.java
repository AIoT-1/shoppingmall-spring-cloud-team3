package com.nhnacademy.shoppingmall.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.shoppingmall.dto.ReviewDto;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.Review;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.exception.review.ReviewNotFoundException;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.repository.ReviewRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import io.swagger.v3.core.util.ObjectMapperFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ReviewServiceImpl reviewService;

    Long id;

    @BeforeEach
    void setUp() {
        id = 1L;
        UserIdStore.setUserId("1");
    }

    @Test
    @DisplayName("리뷰 목록 조회")
    void getReviews() {
        when(reviewRepository.findByProduct_Id(anyLong())).thenReturn(List.of(createReviewFixture(), createReviewFixture()));

        List<ReviewDto.ProductReviewResponse> actual = reviewService.getReviews(1L);

        Mockito.verify(reviewRepository, Mockito.times(1)).findByProduct_Id(anyLong());
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getId()).isEqualTo(1L);
        assertThat(actual.get(0).getComment()).isEqualTo("comment");
        assertThat(actual.get(0).getRating()).isEqualTo(5);
    }

    @Test
    @DisplayName("리뷰 생성")
    void createReview() {
        Review review = createReviewFixture();
        ReviewDto.CreateRequest request = Mockito.mock(ReviewDto.CreateRequest.class);
        when(userRepository.getReferenceById(anyLong())).thenReturn(Mockito.mock(User.class));
        when(productRepository.getReferenceById(anyLong())).thenReturn(Mockito.mock(Product.class));
        when(reviewRepository.save(any())).thenReturn(review);

        Long actual = reviewService.createReview(1L, request);

        verify(userRepository, Mockito.times(1)).getReferenceById(anyLong());
        verify(productRepository, Mockito.times(1)).getReferenceById(anyLong());
        Mockito.verify(reviewRepository, Mockito.times(1)).save(any());
        assertThat(actual).isEqualTo(1L);
    }

    @Test
    @DisplayName("리뷰 수정")
    void updateReview() throws JsonProcessingException {
        Review review = createReviewFixture();
        ReviewDto.UpdateRequest request = ObjectMapperFactory.buildStrictGenericObjectMapper()
                                                                .readValue("{\"comment\":\"comment\"," + " " +
                                                                                    " \"rating\":  5}", ReviewDto.UpdateRequest.class);
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(review));

        reviewService.updateReview(1L, request);

        verify(reviewRepository, Mockito.times(1)).findById(anyLong());
        assertThat(review.getComment()).isEqualTo("comment");
        assertThat(review.getRating()).isEqualTo(5);
    }

    @Test
    @DisplayName("리뷰 삭제")
    void deleteReview() {
        when(reviewRepository.existsById(anyLong())).thenReturn(true);

        reviewService.deleteReview(1L);

        verify(reviewRepository, Mockito.times(1)).existsById(anyLong());
        verify(reviewRepository, Mockito.times(1)).deleteById(anyLong());
    }

    private Review createReviewFixture() {
        Review review = Review.builder().user(Mockito.mock(User.class)).product(Mockito.mock(Product.class)).comment("comment").rating(5).build();
        ReflectionTestUtils.setField(review, "id", 1L);
        return review;
    }

    @Test
    @DisplayName("존재하지 않는 리뷰 삭제")
    void deleteReviewNotExist() {
        when(reviewRepository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> reviewService.deleteReview(1L)).isInstanceOf(ReviewNotFoundException.class);

        verify(reviewRepository, Mockito.times(1)).existsById(anyLong());
        verify(reviewRepository, Mockito.times(0)).deleteById(anyLong());
    }
}