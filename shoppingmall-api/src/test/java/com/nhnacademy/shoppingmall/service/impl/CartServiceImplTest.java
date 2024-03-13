package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.CartDto;
import com.nhnacademy.shoppingmall.enitiy.Cart;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.repository.CartRepository;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    private Long productId = 0L;
    @Mock
    private CartRepository cartRepository;


    @InjectMocks
    private CartServiceImpl cartService;
    @Test
    void getCart() {
        UserIdStore.setUserId("1");
        Product product = createProduct();
        Product product2 = createProduct();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        ReflectionTestUtils.setField(cart1, "id", 1L);
        ReflectionTestUtils.setField(cart2, "id", 2L);
        ReflectionTestUtils.setField(cart1, "product", product);
        ReflectionTestUtils.setField(cart2, "product", product2);
        ReflectionTestUtils.setField(cart1, "quantity", 5);
        ReflectionTestUtils.setField(cart2, "quantity", 10);
        when(cartRepository.findByUser_Id(anyLong())).thenReturn(List.of(cart1, cart2));

        List<CartDto.Read.Response> actual = cartService.getCart();

        Assertions.assertThat(actual).hasSize(2);
        verify(cartRepository, times(1)).findByUser_Id(anyLong());
    }

    private Product createProduct() {
        Product product = Product.builder()
                .modelNumber("modelNumber")
                .modelName("modelName")
                .unitCost(1000)
                .description("description")
                .thumbnail("thumbnail")
                .build();
        ReflectionTestUtils.setField(product, "id", productId++);
        return product;
    }
}