package com.nhnacademy.shoppingmall.exception.user;

import com.nhnacademy.shoppingmall.exception.BadRequestException;

public class UserTerminatedException extends BadRequestException {
    public UserTerminatedException(Long id) {
        super("User is terminated: " + id);
    }
}
