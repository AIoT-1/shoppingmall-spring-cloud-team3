package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
