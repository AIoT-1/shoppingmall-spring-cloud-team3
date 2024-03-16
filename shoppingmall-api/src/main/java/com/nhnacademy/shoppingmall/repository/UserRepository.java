package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findPageByAuth(Pageable pageable, String auth);
    boolean existsByLoginId(String loginId);
    Optional<User> findByLoginId(String loginId);
}