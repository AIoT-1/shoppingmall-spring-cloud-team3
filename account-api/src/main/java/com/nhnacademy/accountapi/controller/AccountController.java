package com.nhnacademy.accountapi.controller;

import com.nhnacademy.accountapi.dto.LoginRequest;
import com.nhnacademy.accountapi.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api/account/users")
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String loginRequest) {
        log.debug("로그인 요청이 수신되었습니다. : {}", loginRequest);

        // 로그인 API를 호출하고 응답을 받음
        String loginUrl = "Http://localhost:8000/api/login"; // 로그인 API의 엔드포인트
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(loginUrl, loginRequest, String.class);

        // 응답의 상태 코드 확인 후 응답 반환
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(responseEntity.getBody());
        } else {
            return ResponseEntity.status(responseEntity.getStatusCode())
                    .body("로그인 실패");
        }
    }

    // 로그아웃 API
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        log.debug("로그아웃 요청이 수신되었습니다. ");

        // 로그아웃 API 호출
        String logoutUrl = "http://localhost:8000/api/logout"; // 로그아웃 API의 엔드포인트
        restTemplate.postForEntity(logoutUrl, null, String.class);

        return ResponseEntity.ok("로그아웃 성공");

    }

    // 사용자 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@RequestHeader("X-USER-ID") String currentUserId,
                                                @PathVariable("id") String userId) {
        log.debug("X-USER-ID:{}", currentUserId);

        // JWT 확인 및 검증


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


    // 사용자 생성 API
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        // 요청 받은 사용자 정보를 Service를 통해 생성하고 응답 반환
        UserResponse response = accountService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 사용자 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest request) {
        // 요청 받은 사용자 정보를 Service를 통해 수정하고 응답 반환
        UserResponse response = accountService.updateUser(userId, request);
        return ResponseEntity.ok(response);
    }

    // 사용자 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        // 해당 ID의 사용자를 삭제하고 응답 반환
        accountService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


}
