package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
