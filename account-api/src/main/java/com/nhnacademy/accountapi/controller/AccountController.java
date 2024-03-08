package com.nhnacademy.accountapi.controller;

import com.nhnacademy.accountapi.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api/account/users")
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@RequestHeader("X-USER-ID") String userId) {
        log.debug("X-USER-ID:{}", userId);

        // Shoppingmall-api로 부터 회원 정보를 조회하는 url 설정
        String shoppingMallApiUrl = "http://localhost:8000/api/user/" + userId;

        try {
            // 회원 정보 조회 API를 호출하고 응답 받음
            ResponseEntity<UserResponse> responseEntity = restTemplate.getForEntity(shoppingMallApiUrl, UserResponse.class);

            // 응답의 상태 코드를 확인 후 응답을 반환
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok(responseEntity.getBody());
            } else {
                // shopping-api에서 에러 났을 때 404나 뭐 405 나오게
                return ResponseEntity.status(responseEntity.getStatusCode())
                        .build();
            }
        } catch (Exception e) {
            // 예외처리
            log.error("shoppingmall-api에서 사용자 정보를 가져오는 중 오류가 발생했습니다. : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
