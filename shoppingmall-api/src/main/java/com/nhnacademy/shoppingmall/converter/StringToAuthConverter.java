package com.nhnacademy.shoppingmall.converter;

import com.nhnacademy.shoppingmall.enums.Auth;
import org.springframework.core.convert.converter.Converter;

public class StringToAuthConverter implements Converter<String, Auth> {
    @Override
    public Auth convert(String auth) {
        return Auth.of(auth);
    }
}