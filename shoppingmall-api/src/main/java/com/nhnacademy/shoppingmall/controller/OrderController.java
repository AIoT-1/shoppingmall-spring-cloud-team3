package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.OrderDetailDto;
import com.nhnacademy.shoppingmall.dto.OrderDto;
import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.enums.PointType;
import com.nhnacademy.shoppingmall.service.OrderDetailService;
import com.nhnacademy.shoppingmall.service.OrderService;
import com.nhnacademy.shoppingmall.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController
{
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final PointService pointService;

    @GetMapping
    public ResponseEntity<PageResponseDto<OrderDto.ReadResponse>> getOrders(@PageableDefault(size = 5, sort = "orderDate") Pageable pageable) {
        Page<OrderDto.ReadResponse> orderPage = orderService.getOrderPage(pageable);
        return ResponseEntity.ok().body(PageResponseDto.fromPage(orderPage));
    }

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody OrderDto.RegisterRequest orderRequest) {
        Long orderId = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping("/{orderId}/details")
    public ResponseEntity<List<OrderDetailDto.ReadResponse>> getOrderDetails(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(orderDetailService.getOrderDetails(orderId));
    }
}
