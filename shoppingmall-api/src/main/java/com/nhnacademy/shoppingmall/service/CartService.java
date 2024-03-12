package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.CartDto;

import java.util.List;

public interface CartService {
    List<CartDto.Read.Response> getCart();

    CartDto.Create.Response addCart(Long productId, Integer quantity);

    void deleteCart(Long cartId);

    CartDto.Update.Response updateCartItemQuantity(Long cartId, CartDto.Update.Request request);
}
