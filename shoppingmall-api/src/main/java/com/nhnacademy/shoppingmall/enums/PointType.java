package com.nhnacademy.shoppingmall.enums;

public enum PointType {
    SAVE("적립"), USE("사용");

    private final String type;

    PointType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
