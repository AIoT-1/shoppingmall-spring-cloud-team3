package com.nhnacademy.accountapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id") // 필드명을 login_id로 수정
    private String loginId;

    private String name;
    private String password;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    private String auth;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    @Column(name = "terminated_yn")
    private String terminatedYn;

    private Long point;
}
