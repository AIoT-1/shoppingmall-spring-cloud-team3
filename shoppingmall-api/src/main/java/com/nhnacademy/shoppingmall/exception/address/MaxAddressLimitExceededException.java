package com.nhnacademy.shoppingmall.exception.address;

import com.nhnacademy.shoppingmall.exception.BadRequestException;

public class MaxAddressLimitExceededException extends BadRequestException {
    public MaxAddressLimitExceededException(int maxAddressLimit) {
        super("Max address limit exceeded: " + maxAddressLimit);
    }
}
