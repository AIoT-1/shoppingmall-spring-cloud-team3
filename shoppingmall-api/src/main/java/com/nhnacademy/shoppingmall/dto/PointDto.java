package com.nhnacademy.shoppingmall.dto;

import com.nhnacademy.shoppingmall.enitiy.Point;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PointDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PointHistoryResponse {
        private Long id;
        private String transactionType;
        private Integer amount;
        private LocalDateTime recordDate;
        public static PointHistoryResponse fromEntity(Point point) {
            PointHistoryResponse pointHistory = new PointHistoryResponse();
            pointHistory.id = point.getId();
            pointHistory.transactionType = point.getTransactionType();
            pointHistory.amount = point.getAmount();
            pointHistory.recordDate = point.getRecordDate();
            return pointHistory;
        }
    }
}
