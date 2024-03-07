package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Read {

        @Getter
        public static class Response {
            private Long id;
            private String loginId;
            private String name;
            private LocalDate birthDate;
            private String auth;
            private LocalDateTime createdAt;
            private LocalDateTime lastLoginAt;
            private Long point;

            public static UserDto.Read.Response fromEntity(User user) {
                Response response = new Response();
                response.id = user.getId();
                response.loginId = user.getLoginId();
                response.name = user.getName();
                response.birthDate = user.getBirthDate();
                response.auth = user.getAuth();
                response.createdAt = user.getCreatedAt();
                response.lastLoginAt = user.getLastLoginAt();
                response.point = user.getPoint();
                return response;
            }
        }

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        @Getter
        public static class Request {
            private String loginId;
            private String name;
            private String password;
            private LocalDate birthDate;
            private String auth;
            private Long point;
        }
        @Getter
        public static class Response {
            private Long id;
            private String loginId;
            private String name;
            private LocalDate birthDate;
            private String auth;
            private LocalDateTime createdAt;
            private LocalDateTime lastLoginAt;
            private Long point;

            public static UserDto.Create.Response fromEntity(User user) {
                Response response = new Response();
                response.id = user.getId();
                response.loginId = user.getLoginId();
                response.name = user.getName();
                response.birthDate = user.getBirthDate();
                response.auth = user.getAuth();
                response.createdAt = user.getCreatedAt();
                response.lastLoginAt = user.getLastLoginAt();
                response.point = user.getPoint();
                return response;
            }

        }
    }
}
