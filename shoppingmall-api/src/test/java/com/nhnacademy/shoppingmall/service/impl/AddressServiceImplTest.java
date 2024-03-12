package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.AddressDto;
import com.nhnacademy.shoppingmall.enitiy.Address;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.exception.address.MaxAddressLimitExceededException;
import com.nhnacademy.shoppingmall.repository.AddressRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    private Long addressId = 1L;
    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private AddressServiceImpl addressService;

    @BeforeEach
    void setUp() {
        UserIdStore.setUserId("1");
    }
    @Test
    @DisplayName("주소 목록 조회 테스트")
    void getAddressList() {

        when(addressRepository.findAllByUser_Id(anyLong())).thenReturn(Arrays.asList(createAddress(), createAddress()));

        List<AddressDto.Read.Response> actual =  addressService.getAddressList();

        assertThat(actual).hasSize(2);
        verify(addressRepository, times(1)).findAllByUser_Id(anyLong());
    }

    @Test
    @DisplayName("주소 등록 테스트")
    void registerAddress() throws Exception{

        Constructor<AddressDto.Create.Request> constructor = AddressDto.Create.Request.class.getDeclaredConstructor(String.class, String.class, String.class, String.class);
        constructor.setAccessible(true);
        AddressDto.Create.Request request = constructor.newInstance("type", "zipCode", "address", "addressDetail");

        User user = User.builder().build();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(addressRepository.countByUser_Id(anyLong())).thenReturn(2); 
        when(addressRepository.save(any(Address.class))).thenReturn(createAddress());

        addressService.registerAddress(request);

        verify(userRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(1)).countByUser_Id(anyLong());
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    @DisplayName("주소 등록 테스트 - 주소 등록 제한 초과")
    void registerAddressExceedMaxAddressLimit() throws Exception{

        AddressDto.Create.Request request = createAddressRequest();

        User user = User.builder().build();
        when(addressRepository.countByUser_Id(anyLong())).thenReturn(5);

        assertThatThrownBy(() -> addressService.registerAddress(request))
                .isInstanceOf(MaxAddressLimitExceededException.class);

        verify(userRepository, times(0)).findById(anyLong());
        verify(addressRepository, times(1)).countByUser_Id(anyLong());
        verify(addressRepository, times(0)).save(any(Address.class));
    }

    @Test
    @DisplayName("주소 등록 테스트 - 사용자 정보 없음")
    void registerAddressUserNotFound() throws Exception{

        AddressDto.Create.Request request = createAddressRequest();
        when(addressRepository.countByUser_Id(anyLong())).thenReturn(2);
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> addressService.registerAddress(request))
                .isInstanceOf(RuntimeException.class);

        verify(userRepository, times(1)).findById(anyLong());
        verify(addressRepository, times(1)).countByUser_Id(anyLong());
        verify(addressRepository, times(0)).save(any(Address.class));
    }

    @Test
    @DisplayName("기본 주소 변경 테스트")
    void updateDefaultAddress() {

//            when(addressRepository.findAllByUser_Id(anyLong())).thenReturn(Arrays.asList(createAddress(), createAddress()));
//
//            AddressDto.Update.Response actual = addressService.updateDefaultAddress(addressId);
//
//            assertThat(actual.getDefaultYn()).isEqualTo("Y");
//            verify(addressRepository, times(1)).findAllByUser_Id(anyLong());
    }

    private Address createAddress() {
        Address address = Address.builder()
                .address("서울시 강남구 "+addressId+"번지")
                .defaultYn("N")
                .build();
        ReflectionTestUtils.setField(address, "id", addressId++);
        return address;
    }

    private AddressDto.Create.Request createAddressRequest() throws Exception{
        Constructor<AddressDto.Create.Request> constructor = AddressDto.Create.Request.class.getDeclaredConstructor(String.class, String.class, String.class, String.class);
        constructor.setAccessible(true);
        AddressDto.Create.Request request = constructor.newInstance("type", "zipCode", "address", "addressDetail");
        return request;
    }
}