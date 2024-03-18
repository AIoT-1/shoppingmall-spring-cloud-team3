package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.CartResponseDTO;
import com.nhnacademy.shoppingmallfront.dto.OrderListResponseDTO;
import com.nhnacademy.shoppingmallfront.dto.OrderRegisterRequestDTO;

import java.util.List;

public interface OrderService {
    void addOrder(OrderRegisterRequestDTO request);
    int calculateTotalCost(List<CartResponseDTO> cart);
    OrderListResponseDTO getOrderList(int page);
}
