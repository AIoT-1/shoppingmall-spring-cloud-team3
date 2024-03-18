package com.nhnacademy.shoppingmall.exception.product;

import com.nhnacademy.shoppingmall.exception.BadRequestException;

public class ProductShortageException extends BadRequestException {
    public ProductShortageException(int productQuantity, int orderQuantity) {
        super("재고가 부족합니다. 현재 재고: " + productQuantity + ", 주문 수량: " + orderQuantity);
    }
}
