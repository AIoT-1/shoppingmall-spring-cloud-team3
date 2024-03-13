package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.CartDto;
import com.nhnacademy.shoppingmall.enitiy.Cart;
import com.nhnacademy.shoppingmall.enitiy.Product;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private CartService cartService;
    @Test
    @DisplayName("장바구니 조회")
    void getCart() throws Exception {
        cartService.getCart();
        mockMvc.perform(get("/api/cart")
                .header("X-USER-ID", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("장바구니 추가")
    void addCart() throws Exception {
        when(cartService.addCart(anyLong(), anyInt())).thenReturn(CartDto.Create.Response.fromEntity(createCart()));
        mockMvc.perform(post("/api/cart")
                .header("X-USER-ID", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                 " \"quantity\": 5," +
                                 " \"productId\":1" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    @Test
    @DisplayName("장바구니 추가 수량 1미만")
    void addCartQuantityLessThanOne() throws Exception {
        mockMvc.perform(post("/api/cart")
                .header("X-USER-ID", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        " \"quantity\": 0," +
                        " \"productId\":1" +
                        "}"))
                .andExpect(status().isBadRequest());
    }

    public Cart createCart() {
        Product product = Product.builder().build();
        User user = User.builder().build();
        ReflectionTestUtils.setField(product, "id", 1L);
        ReflectionTestUtils.setField(user, "id", 1L);
        Cart cart = Cart.builder()
                .product(product)
                .quantity(5)
                .user(user).build();
        ReflectionTestUtils.setField(cart, "id", 1L);
        return cart;
    }

}