package com.nhnacademy.shoppingmall.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhnacademy.shoppingmall.dto.CartDto;
import com.nhnacademy.shoppingmall.enitiy.Cart;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.repository.CartRepository;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import io.swagger.v3.core.util.ObjectMapperFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    private Long productId = 1L;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private UserRepository userRepository;

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

        assertThat(actual).hasSize(2);
        verify(cartRepository, times(1)).findByUser_Id(anyLong());
    }

    @Test
    void addCart() {
        UserIdStore.setUserId("1");
        Product product = createProduct();
        Cart cart = new Cart();
        User user = User.builder().loginId("1").build();
        ReflectionTestUtils.setField(cart, "id", 1L);
        ReflectionTestUtils.setField(cart, "product", product);
        ReflectionTestUtils.setField(cart, "quantity", 5);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        CartDto.Create.Response actual = cartService.addCart(product.getId(), 5);

        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getProductId()).isEqualTo(product.getId());
        assertThat(actual.getQuantity()).isEqualTo(5);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void deleteCart() {
        UserIdStore.setUserId("1");
        Cart cart = new Cart();
        ReflectionTestUtils.setField(cart, "id", 1L);
        when(cartRepository.existsById(anyLong())).thenReturn(true);

        cartService.deleteCart(1L);

        verify(cartRepository, times(1)).existsById(anyLong());
        verify(cartRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void updateCartItemQuantity() throws JsonProcessingException {
        UserIdStore.setUserId("1");
        Cart cart = Cart.builder()
                .product(createProduct())
                .user(User.builder().loginId("1").build())
                .quantity(5).build();
        ReflectionTestUtils.setField(cart, "id", 1L);
        CartDto.Update.Request request = ObjectMapperFactory.buildStrictGenericObjectMapper().readValue("{\"quantity\":10}", CartDto.Update.Request.class);
        when(cartRepository.findById(anyLong())).thenReturn(java.util.Optional.of(cart));

        CartDto.Update.Response actual = cartService.updateCartItemQuantity(1L, request);

        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getQuantity()).isEqualTo(10);
        verify(cartRepository, times(1)).findById(anyLong());
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