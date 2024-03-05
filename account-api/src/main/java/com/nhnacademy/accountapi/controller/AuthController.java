package com.nhnacademy.accountapi.controller;

import com.nhnacademy.accountapi.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// 인증을 처리하는 컨트롤러
@RestController
public class AuthController {

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody String username) {
        // 사용자 인증 과정
        // 유효한 사용자라면 JWT 생성 및 반환
        String token = JwtUtil.generateToken(username);
        return ResponseEntity.ok(token);
    }
}

/*
JWT 토큰을 생성하고 사용자 인증에 관련된 기능을 처리하기 위해 JWTUtil과 AuthController 클래스를 작성
사용자 로그인 요청받고, 해당 요청에 대한 사용자 인증 수행 후 유효하면 토큰 받고, 아니면 못받음.
이 클래스는 해당 토큰을 사용해서 사용자 정보 조회에 대한 요청을 처리
 */
