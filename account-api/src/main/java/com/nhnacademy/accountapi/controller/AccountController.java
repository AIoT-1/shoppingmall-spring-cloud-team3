package com.nhnacademy.accountapi.controller;

import com.nhnacademy.accountapi.domain.User;
import com.nhnacademy.accountapi.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/account/users")
public class AccountController {

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@RequestHeader("X-USER-ID") String userId){
        log.debug("X-USER-ID:{}",userId);
        //TODO#6 회원조회 API를 구현합니다.
        //UserResponse <-- 각 팀별로 설계한 회원 스키마를 고려하여 수정합니다.
        //X-USER-ID는 Gateway에서 access-token을 검증 후 valid한 token이면 jwt의 payload의 userId를 Header에  X-USER-ID로 추가 합니다.
        //회원은 shoppingmall-api 서버에 회원을 조회할 수 있는 api를 개발<-- 해당 API를 호출 합니다.
        User user = User.createUser("nhnacademy", "엔에이치엔아카데미", "password"); // 임시 사용자 정보
        UserResponse userResponse = new UserResponse(user.getUserId(), user.getUserName()); // 적절한 회원 정보로 UserResponse 객체 생성

        return ResponseEntity.ok(userResponse);
    }

    // 실제로는 shoppingmall-api 서버에서 회원 정보를 조회하는 메서드를 호출해야 합니다.
    private User getUserFromShoppingmallApi(String userId) {
        // 여기서는 임시로 사용자 정보를 생성하여 반환하는 것으로 대체합니다.
        return User.createUser(userId, "사용자 이름", "비밀번호");
    }

}
