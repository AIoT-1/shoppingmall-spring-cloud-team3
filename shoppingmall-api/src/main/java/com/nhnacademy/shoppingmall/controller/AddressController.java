package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.AddressDto;
import com.nhnacademy.shoppingmall.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AddressDto.Create.Response> createAddress(AddressDto.Create.Request request){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.registerAddress(request));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressDto.Update.Response> updateAddress(@PathVariable("addressId")Long addressId, @RequestBody AddressDto.Update.Request request){
        return ResponseEntity.ok().body(addressService.updateAddress(addressId, request));
    }

    @PutMapping("/{addressId}/default")
    public ResponseEntity<AddressDto.Update.Response> updateDefaultAddress(@PathVariable("addressId")Long addressId){
        return ResponseEntity.ok().body(addressService.updateDefaultAddress(addressId));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("addressId")Long addressId){
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }

}
