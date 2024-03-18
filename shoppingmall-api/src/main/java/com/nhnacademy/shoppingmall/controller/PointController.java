package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.dto.PageResponseDto;
import com.nhnacademy.shoppingmall.dto.PointDto;
import com.nhnacademy.shoppingmall.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {

    private final PointService pointService;
    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final String DEFAULT_SORT_PARAM = "recordDate";
    @GetMapping
    public ResponseEntity<PageResponseDto<PointDto.PointHistoryResponse>>
    getPointHistory(@ParameterObject @PageableDefault(size = DEFAULT_PAGE_SIZE, sort = DEFAULT_SORT_PARAM) Pageable pageable) {
        PageResponseDto<PointDto.PointHistoryResponse> pointHistoryPage = pointService.getPointHistoryPage(pageable);
        return ResponseEntity.ok().body(pointHistoryPage);
    }
}
