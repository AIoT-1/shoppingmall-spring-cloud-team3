package com.nhnacademy.accountapi.controller;

import com.nhnacademy.accountapi.dto.TokenResponse;
import com.nhnacademy.accountapi.security.details.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/account/users")
public class AccountController {

    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public AccountController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TokenResponse> getUser(@RequestHeader("X-USER-ID") String userId){
        log.debug("X-USER-ID:{}",userId);
        //TODO#6 회원조회 API를 구현합니다.
        //UserResponse <-- 각 팀별로 설계한 회원 스키마를 고려하여 수정합니다.
        //X-USER-ID는 Gateway에서 access-token을 검증 후 valid한 token이면 jwt의 payload의 userId를 Header에  X-USER-ID로 추가 합니다.
        //회원은 shoppingmall-api 서버에 회원을 조회할 수 있는 api를 개발<-- 해당 API를 호출 합니다.
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        String accessToken = generateJwtToken(userDetails); // JWT 토큰 생성 메서드 호출
        TokenResponse tokenResponse = new TokenResponse(accessToken, "Bearrer", 3600); // 토큰 유효시간
        //UserResponse userResponse = new UserResponse(userDetails.getUsername(), userDetails.getUsername());
        // 생성된 UserResponse를 ResponseEntity에 담아 반환
        return ResponseEntity.ok(tokenResponse);
    }

    // JWT Token 생성 메서드
    private String generateJwtToken(UserDetails userDetails) {
        // jwt 토큰을 생성하는 로직 구현 부분
        // 아래는 임시로 샘플 JWT 토큰을 반환하는 부분
        return "sample_jwt_token";
    }
}
