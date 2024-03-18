package com.nhnacademy.shoppingmall.enitiy;

import com.nhnacademy.shoppingmall.exception.user.PointShortageException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login_id")
    private String loginId;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "auth")
    private String auth;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;
    @Column(name = "terminated_yn")
    @ColumnDefault("'N'")
    private String terminatedYn;
    @Column(name = "point")
    private Long point;

    @Builder
    public User(String loginId, String name, String password, LocalDate birthDate, String auth, Long point) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
        this.auth = auth;
        this.point = point;
    }

    public void payment(int price) {
        if(this.point < price) {
            throw new PointShortageException(this.point, price);
        }
        this.point -= price;
    }
    public void earnPoint(int point) {
        this.point += point;
    }

    public void updateUser(String name, String password, LocalDate birthDate) {
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
    }
    public void terminate() {
        this.terminatedYn = "Y";
    }
    public boolean isTerminated() {
        return "Y".equals(this.terminatedYn);
    }
    
}
