package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>{

    List<Cart> findByUser_Id(Long userId);

    boolean existsByUser_IdAndProduct_Id(Long userId, Long productId);
}
