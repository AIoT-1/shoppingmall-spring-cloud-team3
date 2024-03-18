package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.dto.OrderDto;
import com.nhnacademy.shoppingmall.service.OrderDetailService;
import com.nhnacademy.shoppingmall.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OrderService orderService;
    @MockBean
    OrderDetailService orderDetailService;

    @Test
    void getOrderPage() throws Exception {
        Page<OrderDto.ReadResponse> orderPage = Page.empty();
        when(orderService.getOrderPage(any())).thenReturn(orderPage);
        mockMvc.perform(get("/api/orders")
                        .header("X-USER-ID", "1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void createOrder() throws Exception {
        OrderDto.RegisterRequest orderRequest = new OrderDto.RegisterRequest(1L, List.of(1L, 2L));
        when(orderService.createOrder(any())).thenReturn(1L);
        mockMvc.perform(post("/api/orders")
                        .header("X-USER-ID", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(orderRequest)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void getOrderDetails() throws Exception {
        mockMvc.perform(get("/api/orders/1/details")
                        .header("X-USER-ID", "1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}