package com.nhnacademy.shoppingmall.enums;

public enum Auth {
    ROLE_ADMIN("admin"), ROLE_USER("user");
    private String value;
    Auth(String value) {
        this.value = value;
    }

    public static Auth of(String value) {
        if (value.equals("admin")) {
            return ROLE_ADMIN;
        } else if (value.equals("user")) {
            return ROLE_USER;
        } else {
            throw new IllegalArgumentException("없는 권한입니다 : " + value);
        }
    }


}
