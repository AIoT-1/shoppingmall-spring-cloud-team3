package com.nhnacademy.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    // 사용자 정보 가져오는 메서드
    public String getUserInfo(String userId) {
        String url = "http://user-service-url/users/" + userId;
        // 사용자 정보 제공하는 서비스 엔드포인트 url;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) { // 응답 처리
            return responseEntity.getBody(); // 바디를 받고 응답오면 사용자 정보 반환
        } else {
            return null;
        }
    }
}
