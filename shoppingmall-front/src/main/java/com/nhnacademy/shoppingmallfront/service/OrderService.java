package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.OrderRegisterRequestDTO;

public interface OrderService {
    void addOrder(OrderRegisterRequestDTO request);
}
