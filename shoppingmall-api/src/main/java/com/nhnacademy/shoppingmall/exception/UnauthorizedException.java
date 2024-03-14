package com.nhnacademy.shoppingmall.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("Unauthorized");
    }
}
