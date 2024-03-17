package com.nhnacademy.shoppingmall.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.dto.OrderDto;
import com.nhnacademy.shoppingmall.enitiy.*;
import com.nhnacademy.shoppingmall.exception.user.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.*;
import com.nhnacademy.shoppingmall.service.PointService;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private PointRepository pointRepository;
    @Mock
    private PointService pointService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("주문 생성 성공")
    void createOrderSuccess() throws Exception {
        UserIdStore.setUserId("1");
        User user = createUser();
        Order order = createOrder();
        Product product = createProduct();
        Cart cart = Cart.builder().product(product).user(user).quantity(4).build();
        OrderDto.RegisterRequest request = objectMapper.readValue("{\"cartIdList\":[1,2], \"addressId\" : 1}", OrderDto.RegisterRequest.class);

        when(addressRepository.findById(anyLong())).thenReturn(Optional.of(createAddress()));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(orderRepository.save(any())).thenReturn(order);
        when(cartRepository.findAllById(any())).thenReturn(Arrays.asList(cart, cart));

        orderService.createOrder(request);

        verify(userRepository, times(1)).findById(anyLong());
        verify(orderRepository, times(1)).save(any());
        verify(cartRepository, times(1)).findAllById(any());
        verify(cartRepository, times(1)).deleteAll(any());
        verify(orderDetailRepository, times(1)).saveAll(any());
    }

    @Test
    @DisplayName("주문 생성 실패 - 사용자 없음")
    void createOrderFailUserNotFound() throws JsonProcessingException {
        UserIdStore.setUserId("1");
        OrderDto.RegisterRequest request = objectMapper.readValue("{\"cartIdList\":[1,2]}", OrderDto.RegisterRequest.class);

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> orderService.createOrder(request));
    }

    @Test
    @DisplayName("주문 페이지 조회 성공")
    void getOrderPageSuccess() {
        UserIdStore.setUserId("1");
        when(orderRepository.findByUser_Id(anyLong(), any())).thenReturn(Page.empty());

        orderService.getOrderPage(PageRequest.of(0, 10));

        verify(orderRepository, times(1)).findByUser_Id(anyLong(), any());
    }

    private User createUser() {
        return User.builder().loginId("1").point(10000L).build();
    }

    private Order createOrder() {
        return Order.builder()
                .user(createUser())
                .address(createAddress())
                .build();
    }
    private Address createAddress() {
        return Address.builder()
                .zipCode("zipCode")
                .address("address")
                .addressDetail("addressDetail")
                .build();
    }

    private Product createProduct() {
        return Product.builder()
                .modelNumber("modelNumber")
                .modelName("modelName")
                .unitCost(1000)
                .quantity(10)
                .description("description")
                .thumbnail("thumbnail")
                .build();
    }

}