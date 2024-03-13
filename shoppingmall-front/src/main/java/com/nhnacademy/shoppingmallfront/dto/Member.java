package com.nhnacademy.shoppingmallfront.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String loginId;
    private String name;
    private String password;
    private Date birthDate;
    private String auth;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    private String isTerminated;
    private Long point;
}
