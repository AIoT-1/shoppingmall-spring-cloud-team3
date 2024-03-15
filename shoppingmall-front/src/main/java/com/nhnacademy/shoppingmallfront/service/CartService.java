package com.nhnacademy.shoppingmallfront.service;

import com.nhnacademy.shoppingmallfront.dto.CartModifyRequestDTO;
import com.nhnacademy.shoppingmallfront.dto.CartRegisterRequestDTO;
import com.nhnacademy.shoppingmallfront.dto.CartResponseDTO;

import java.util.List;

public interface CartService {
    List<CartResponseDTO> getCart();
    void addCart(CartRegisterRequestDTO request);
    void updateCart(Long cartId, CartModifyRequestDTO request);
    void deleteCart(Long cartId);
    int calculateTotalCost(List<CartResponseDTO> cart);
}
