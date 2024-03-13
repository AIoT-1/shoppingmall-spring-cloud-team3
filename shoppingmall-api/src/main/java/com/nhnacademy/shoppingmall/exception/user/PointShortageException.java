package com.nhnacademy.shoppingmall.exception.user;

import com.nhnacademy.shoppingmall.exception.BadRequestException;

public class PointShortageException extends BadRequestException {
    public PointShortageException(Long userPoint, int orderPrice) {
        super("포인트가 부족합니다. 현재 포인트: " + userPoint + ", 주문 금액: " + orderPrice);
    }
}
