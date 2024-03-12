package com.nhnacademy.shoppingmall.exception.cart;

import com.nhnacademy.shoppingmall.exception.DuplicateException;

public class DuplicateCartException extends DuplicateException {
    public DuplicateCartException(Long productId) {
        super("Duplicate cart: " + productId);
    }
}
