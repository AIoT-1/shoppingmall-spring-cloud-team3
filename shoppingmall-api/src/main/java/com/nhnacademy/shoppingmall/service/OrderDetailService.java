package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto.ReadResponse> getOrderDetails(Long orderId);
}
