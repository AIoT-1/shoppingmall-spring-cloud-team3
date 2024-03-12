package com.nhnacademy.accountapi.service;

import com.nhnacademy.accountapi.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
            restTemplate.put(updateUserUrl, request);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("사용자가 존재하지 않습니다."); // 존재 x
        } else {
                throw new RuntimeException("사용자가 수정에 실패하였습니다."); // 실패
            }
        }
    }

    // 사용자 삭제 로직
    public void deleteUser(String userId) {
        // 사용자 삭제 API 호출
        String deleteUserUrl = "http://localhost:8000/api/account/users/" + userId;
        try {
            restTemplate.delete(deleteUserUrl);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("사용자가 존재하지 않습니다.");
            } else {
                throw new RuntimeException("사용자가 수정에 실패 하였습니다.");
            }
        }
    }
}
