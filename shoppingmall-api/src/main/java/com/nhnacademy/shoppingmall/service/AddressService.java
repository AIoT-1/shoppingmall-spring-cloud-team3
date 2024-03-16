package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.AddressDto;

import java.util.List;

public interface AddressService {


    List<AddressDto.Read.Response> getAddressList();

    AddressDto.Create.Response registerAddress(AddressDto.Create.Request request);

    AddressDto.Update.Response updateDefaultAddress(Long addressId);

    AddressDto.Update.Response updateAddress(Long addressId, AddressDto.Update.Request request);

    void deleteAddress(Long addressId);
}
