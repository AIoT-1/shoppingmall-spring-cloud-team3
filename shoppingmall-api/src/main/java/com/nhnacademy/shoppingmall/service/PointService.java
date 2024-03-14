package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.PointDto;
import com.nhnacademy.shoppingmall.enitiy.Order;
import com.nhnacademy.shoppingmall.enitiy.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PointService {
    Page<PointDto.PointHistoryResponse> getPointHistoryPage(Pageable pageable);
    void accruePointByOrder(User user, Order order);
}
