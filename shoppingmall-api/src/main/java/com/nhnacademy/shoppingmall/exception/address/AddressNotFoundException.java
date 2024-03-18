package com.nhnacademy.shoppingmall.exception.address;

import com.nhnacademy.shoppingmall.exception.NotFoundException;

public class AddressNotFoundException extends NotFoundException {
    public AddressNotFoundException(Long addressId) {
        super("Address not found: " + addressId);
    }
}
