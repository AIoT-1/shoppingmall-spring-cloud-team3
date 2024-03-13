package com.nhnacademy.shoppingmall.exception.user;

import com.nhnacademy.shoppingmall.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Long id) {
        super("User not found: " + id);
    }
}
