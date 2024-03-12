package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.OrderDto;
import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController
{
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<PageResponseDto<OrderDto.ReadResponse>> getOrders(@PageableDefault(size = 5, sort = "orderDate") Pageable pageable) {
        Page<OrderDto.ReadResponse> orderPage = orderService.getOrderPage(pageable);
        return ResponseEntity.ok().body(PageResponseDto.fromPage(orderPage));
    }

    @PostMapping
    public OrderDto.RegisterResponse createOrder(OrderDto.RegisterRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }
}
