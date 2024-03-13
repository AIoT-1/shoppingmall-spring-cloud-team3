package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.dto.PointDto;
import com.nhnacademy.shoppingmall.repository.PointRepository;
import com.nhnacademy.shoppingmall.service.PointService;
import com.nhnacademy.shoppingmall.thread.UserIdStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    @Override
    public Page<PointDto.PointHistoryResponse> getPointHistoryPage(Pageable pageable) {

        return pointRepository.findByUser_Id(UserIdStore.getUserId(), pageable)
                .map(PointDto.PointHistoryResponse::fromEntity);
    }
}
