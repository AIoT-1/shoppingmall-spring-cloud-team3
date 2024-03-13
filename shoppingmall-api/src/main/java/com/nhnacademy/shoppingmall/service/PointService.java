package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.dto.PointDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PointService {
    Page<PointDto.PointHistoryResponse> getPointHistoryPage(Pageable pageable);
}
