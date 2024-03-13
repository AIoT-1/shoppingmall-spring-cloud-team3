package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.enitiy.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrderId(Long orderId);
}
