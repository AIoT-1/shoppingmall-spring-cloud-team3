package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.OrderDetailDto;
import com.nhnacademy.shoppingmall.enitiy.OrderDetail;
import com.nhnacademy.shoppingmall.exception.order.OrderNotFoundException;
import com.nhnacademy.shoppingmall.repository.OrderDetailRepository;
import com.nhnacademy.shoppingmall.repository.OrderRepository;
import com.nhnacademy.shoppingmall.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Override
    @Transactional(readOnly = true)
    public List<OrderDetailDto.ReadResponse> getOrderDetails(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException(orderId);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        return orderDetailList.stream()
                .map(OrderDetailDto.ReadResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
