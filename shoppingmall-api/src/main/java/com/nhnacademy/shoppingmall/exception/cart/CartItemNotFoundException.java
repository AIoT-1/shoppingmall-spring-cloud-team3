package com.nhnacademy.shoppingmall.exception.cart;

import com.nhnacademy.shoppingmall.exception.NotFoundException;

public class CartItemNotFoundException extends NotFoundException {
    public CartItemNotFoundException(Long cartId) {
        super("Cart item not found: " + cartId);
    }
}
