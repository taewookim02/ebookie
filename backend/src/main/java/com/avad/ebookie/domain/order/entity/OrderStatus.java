package com.avad.ebookie.domain.order.entity;

public enum OrderStatus {
    PENDING,    // 주문 대기
    PAID,       // 결제 완료
    CANCELLED,  // 주문 취소
    REFUNDED    // 환불됨
}
