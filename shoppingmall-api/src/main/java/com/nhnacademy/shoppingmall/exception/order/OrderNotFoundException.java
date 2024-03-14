package com.nhnacademy.shoppingmall.exception.order;

import com.nhnacademy.shoppingmall.exception.NotFoundException;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(Long orderId) {
        super("Order not found: " + orderId);
    }
}
