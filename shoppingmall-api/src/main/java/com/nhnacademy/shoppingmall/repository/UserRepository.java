package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findPageByAuth(Pageable pageable, String auth);

}
