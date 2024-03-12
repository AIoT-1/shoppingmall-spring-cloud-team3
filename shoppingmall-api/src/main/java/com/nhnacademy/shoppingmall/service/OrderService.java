package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDto.RegisterResponse createOrder(OrderDto.RegisterRequest request);

    Page<OrderDto.ReadResponse> getOrderPage(Pageable pageable);
}
