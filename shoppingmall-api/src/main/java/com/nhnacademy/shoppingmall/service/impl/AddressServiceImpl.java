package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.AddressDto;
import com.nhnacademy.shoppingmall.enitiy.Address;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.exception.address.AddressNotFoundException;
import com.nhnacademy.shoppingmall.exception.address.MaxAddressLimitExceededException;
import com.nhnacademy.shoppingmall.exception.user.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.AddressRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.AddressService;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private static final int MAX_ADDRESS_COUNT = 5;
    @Override
    public List<AddressDto.Read.Response> getAddressList() {
        Long userId = UserIdStore.getUserId();
        return addressRepository.findAllByUser_Id(userId)
                .stream()
                .map(AddressDto.Read.Response::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto.Create.Response registerAddress(AddressDto.Create.Request request) {
        Long userId = UserIdStore.getUserId();

        int addressCount = addressRepository.countByUser_Id(userId);
        if(addressCount >= MAX_ADDRESS_COUNT){
            throw new MaxAddressLimitExceededException(MAX_ADDRESS_COUNT);
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Address address = request.toEntity(user);
        Address savedAddress = addressRepository.save(address);

        return AddressDto.Create.Response.fromEntity(savedAddress);
    }

    @Override
    public AddressDto.Update.Response updateDefaultAddress(Long addressId) {
        List<Address> addressList = addressRepository.findAllByUser_Id(UserIdStore.getUserId());
        // addressId에 해당하는 주소를 찾기
        Address targetAddress = addressList.stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new AddressNotFoundException(addressId));
        // 기존에 defaultYn이 Y인 주소를 N으로 변경
        addressList.stream()
                .filter(a -> a.getDefaultYn().equals("Y"))
                .forEach(a -> a.setDefaultYn("N"));
        // targetAddress defaultYn을 Y로 변경
        targetAddress.setDefaultYn("Y");
        return AddressDto.Update.Response.fromEntity(targetAddress);
    }

    @Override
    public AddressDto.Update.Response updateAddress(Long addressId, AddressDto.Update.Request request) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));
        address.update(request);
        return AddressDto.Update.Response.fromEntity(address);
    }

}
