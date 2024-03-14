package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReviewService reviewService;

    @Test
    void getReviews() throws Exception {
        mockMvc.perform(get("/api/products/1/reviews")
                        .header("X-USER-ID", "1")
                )
                .andExpect(status().isOk());
    }

    @Test
    void createReview() throws Exception {
        mockMvc.perform(post("/api/products/1/reviews")
                        .header("X-USER-ID", "1")
                        .contentType("application/json")
                        .content("{" +
                                 "  \"rating\": 5," +
                                 "  \"content\": \"좋아요\"" +
                                 "}"))
                .andExpect(status().isCreated());
    }

    @Test
    void updateReview() throws Exception {
        mockMvc.perform(put("/api/products/1/reviews/1")
                        .header("X-USER-ID", "1")
                        .contentType("application/json")
                        .content("{" +
                                 "  \"rating\": 3," +
                                 "  \"content\": \"수정\"" +
                                 "}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteReview() throws Exception {
        mockMvc.perform(delete("/api/products/1/reviews/1")
                        .header("X-USER-ID", "1"))
                .andExpect(status().isNoContent());
    }
}