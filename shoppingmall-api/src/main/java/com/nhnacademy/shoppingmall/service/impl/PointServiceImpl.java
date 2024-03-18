package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.PointDto;
import com.nhnacademy.shoppingmall.enitiy.Order;
import com.nhnacademy.shoppingmall.enitiy.Point;
import com.nhnacademy.shoppingmall.enitiy.User;
import com.nhnacademy.shoppingmall.enums.PointType;
import com.nhnacademy.shoppingmall.repository.PointRepository;
import com.nhnacademy.shoppingmall.service.PointService;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private static final Integer POINT_ACCRUE_RATE = 10;
    private final PointRepository pointRepository;
    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<PointDto.PointHistoryResponse> getPointHistoryPage(Pageable pageable) {
        Page<PointDto.PointHistoryResponse> page = pointRepository.findByUser_Id(UserIdStore.getUserId(), pageable)
                .map(PointDto.PointHistoryResponse::fromEntity);
        return PageResponseDto.fromPage(page);
    }

    @Override
    @Async
    @Transactional
    public void accruePointByOrder(User user, Order order) {
        Integer accuratePoint = accruePointByOrderPrice(order.getPrice());
        user.earnPoint(accuratePoint);
        pointRepository.save(Point.builder()
                .user(user)
                .transactionType(PointType.SAVE.getType())
                .amount(accuratePoint)
                .build());
    }

    private Integer accruePointByOrderPrice(Integer price) {
        return price * POINT_ACCRUE_RATE / 100;
    }
}
