package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
