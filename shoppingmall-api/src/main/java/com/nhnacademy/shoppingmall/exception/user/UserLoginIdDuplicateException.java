package com.nhnacademy.shoppingmall.exception.user;

import com.nhnacademy.shoppingmall.exception.DuplicateException;

public class UserLoginIdDuplicateException extends DuplicateException {
    public UserLoginIdDuplicateException(String loginId) {
        super("User LoginId is duplicated: " + loginId);
    }
}
