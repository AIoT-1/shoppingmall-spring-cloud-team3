package com.nhnacademy.accountapi.controller;

import com.nhnacademy.accountapi.dto.LoginRequest;
import com.nhnacademy.accountapi.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.desktop.UserSessionListener;
import java.awt.print.Pageable;

@Slf4j
@RestController
@RequestMapping("/api/account/users")
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        log.debug("로그인 요청이 수신되었습니다. : {}", loginRequest.getUsername());

        // 로그인 API를 호출하고 응답을 받음
        String loginUrl = "Http://localhost:8000/api/login"; // 로그인 API의 엔드포인트
        ResponseEntity<LoginResponse> responseEntity = restTemplate.postForEntity(loginUrl, loginRequest, LoginResponse.class);

        // 응답의 상태 코드 확인 후 응답 반환
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(responseEntity.getBody());
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode())
                    .body(new LogoutResponse("Logout failed")); // 실패한 경우 메시지만 반환
        }
    }



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
