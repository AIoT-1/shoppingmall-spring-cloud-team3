package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.ProductDetailDTO;
import com.nhnacademy.shoppingmallfront.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final RestTemplate restTemplate;

    @Value("${shoppingmall_server_url}")
    private String serverURL;

    public ReviewServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ReviewDTO> getReviews(int productId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-USER-ID", "1");

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<List<ReviewDTO>> response = this.restTemplate.exchange(
                serverURL + "/products/" + productId + "/reviews",
                HttpMethod.GET,
                requestEntity, new ParameterizedTypeReference<List<ReviewDTO>>() {}
        );
        return response.getBody();
    }

    @Override
    public void addReview(int rating, String comment, int productId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-USER-ID", "1");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("rating", rating);
        requestBody.put("comment", comment);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                serverURL + "/products/" + productId + "/reviews",
                HttpMethod.POST,
                requestEntity,
                String.class);
    }

    @Override
    public void deleteReview(int reviewId, int productId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-USER-ID", "1");

        HttpEntity<Long> requestEntity = new HttpEntity<>(httpHeaders);

        this.restTemplate.exchange(
                serverURL + "/products/" + productId + "/reviews/" + reviewId,
                HttpMethod.DELETE,
                requestEntity,
                Void.class);
    }

    @Override
    public void updateReview(int rating, String comment, int reviewId, int productId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-USER-ID", "1");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("rating", rating);
        requestBody.put("comment", comment);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                serverURL + "/products/" + productId + "/reviews/" + reviewId,
                HttpMethod.PUT,
                requestEntity,
                String.class);
    }
}
