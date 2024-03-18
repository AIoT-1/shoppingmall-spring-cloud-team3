package com.nhnacademy.shoppingmall.exception.product;

import com.nhnacademy.shoppingmall.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
        super("Product not found: " + id);
    }
}
