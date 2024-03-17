package com.nhnacademy.shoppingmallfront.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String loginId;
    private String name;
    private LocalDate birthDate;
    private String auth;
    private LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;
    private int point;
}
