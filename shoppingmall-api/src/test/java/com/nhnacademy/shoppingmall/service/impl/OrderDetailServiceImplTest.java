package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.OrderDetailDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderDetailServiceImplTest {
    private static Long productId = 0L;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @InjectMocks
    private OrderDetailServiceImpl orderDetailService;
//    public List<OrderDetailDto.ReadResponse> getOrderDetails(Long orderId) {
//        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
//        return orderDetailList.stream()
//                .map(OrderDetailDto.ReadResponse::fromEntity)
//                .collect(Collectors.toList());
    @Test
    @DisplayName("주문 상세 조회 테스트")
    void getOrderDetails() {
        // given
        Long orderId = 1L;
        OrderDetail orderDetail = OrderDetail.builder()
                .order(null)
                .product(null)
                .quantity(1)
                .build();
        when(orderDetailRepository.findByOrderId(orderId)).thenReturn(List.of(orderDetail));
        // when
        List<OrderDetailDto.ReadResponse> actual = orderDetailService.getOrderDetails(orderId);
        // then
        assertEquals(1, actual.size());

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
}