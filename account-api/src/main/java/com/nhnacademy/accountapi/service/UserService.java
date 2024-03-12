package com.nhnacademy.accountapi.service;

import com.nhnacademy.accountapi.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    // 사용자 생성 로직
    public UserResponse createUser(UserRequest request) {
        // 사용자 생성 API 호출
        String createUserUrl = "http://localhost:8000/api/account/users";
        return restTemplate.postForObject(createUserUrl, request, UserResponse.class);
    }

    // 사용자 수정 로직
    public void updateUser(String userId, UserRequest request) {
        // 사용자 수정 API 호출
        String updateUserUrl = "http://localhost:8000/api.account/users/" + userId;
        try {

        }
        // 응답 확인 후 변환
        if (updateUserResponse.getStatusCode().is2xxSuccessful()) {
            return updateUserResponse.getBody();
        } else {
            // 예외처리 실패할 때
            throw new RuntimeException("사용자 수정을 실패하였습니다.");
        }
    }

    // 사용자 삭제 로직
    public void deleteUser(String userId) {
        // 사용자 삭제 API 호출
        String deleteUserUrl = "http://localhost:8000/api/account/users/" + userId;
        restTemplate.delete(deleteUserUrl);
    }
}
