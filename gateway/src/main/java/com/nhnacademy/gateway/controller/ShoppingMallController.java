package com.nhnacademy.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/api")
@RestController
public class ShoppingMallController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${shoppingmall.api.url}")
    private String shoppingMallApiUrl;

    @PostMapping("/shoppingmall")
    public ResponseEntity<?> accessShoppingMallAPI(@RequestBody Object requestPayload) {
        // 쇼핑몰 API에 요청을 보냅니다.
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(
                shoppingMallApiUrl, requestPayload, Object.class);

        // 쇼핑몰 API에서 받은 응답을 그대로 클라이언트에게 전달합니다.
        return ResponseEntity.status(responseEntity.getStatusCode())
                .headers(responseEntity.getHeaders())
                .body(responseEntity.getBody());
    }
}