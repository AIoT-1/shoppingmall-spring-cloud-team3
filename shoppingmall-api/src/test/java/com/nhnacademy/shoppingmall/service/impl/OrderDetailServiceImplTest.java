package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.OrderDetailDto;
import com.nhnacademy.shoppingmall.enitiy.Address;
import com.nhnacademy.shoppingmall.enitiy.Order;
import com.nhnacademy.shoppingmall.enitiy.OrderDetail;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.repository.OrderDetailRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailServiceImplTest {
    private static Long productId = 0L;
    private static Long orderId = 0L;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @InjectMocks
    private OrderDetailServiceImpl orderDetailService;

    @Test
    @DisplayName("주문 상세 조회 테스트")
    void getOrderDetails() {
        Long orderId = 1L;
        OrderDetail orderDetail = OrderDetail.builder()
                .order(createOrder())
                .product(createProduct())
                .quantity(1)
                .build();
        OrderDetail orderDetail2 = OrderDetail.builder()
                .order(createOrder())
                .product(createProduct())
                .quantity(1)
                .build();
        when(orderDetailRepository.findByOrderId(orderId)).thenReturn(List.of(orderDetail, orderDetail2));
        List<OrderDetailDto.ReadResponse> actual = orderDetailService.getOrderDetails(orderId);

        assertThat(actual.size()).isEqualTo(2);
        assertThat(actual.get(0).getProductId()).isEqualTo(1L);
        assertThat(actual.get(1).getProductId()).isEqualTo(2L);

    }

    private Product createProduct() {
        productId++;

        Product product = Product.builder()
                .modelNumber("modelNumber")
                .modelName("modelName")
                .unitCost(1000)
                .description("description")
                .thumbnail("thumbnail")
                .build();
        ReflectionTestUtils.setField(product, "id", productId);
        return product;
    }
    private Order createOrder() {
        orderId++;
        Order order =  Order.builder()
                .user(null)
                .address(createAddress())
                .build();
        ReflectionTestUtils.setField(order, "id", orderId);
        return  order;

    }
    private Address createAddress() {
        return Address.builder()
                .zipCode("zipCode")
                .address("address")
                .addressDetail("addressDetail")
                .build();
    }
}