package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.AddressDto;
import com.nhnacademy.shoppingmall.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;
    @GetMapping
    public List<AddressDto.Read.Response> getAddressList(){

        return addressService.getAddressList();
    }

    @PostMapping
    public AddressDto.Create.Response createAddress(AddressDto.Create.Request request){
        return addressService.registerAddress(request);
    }

    @PutMapping("/{addressId}")
    public AddressDto.Update.Response updateAddress(@PathVariable("addressId")Long addressId, @RequestBody AddressDto.Update.Request request){
        return addressService.updateAddress(addressId, request);
    }

    @PutMapping("/{addressId}/default")
    public AddressDto.Update.Response updateDefaultAddress(@PathVariable("addressId")Long addressId){
        return addressService.updateDefaultAddress(addressId);
    }
}
