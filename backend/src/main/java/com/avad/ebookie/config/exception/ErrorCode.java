package com.avad.ebookie.config.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // GENERAL
    NOT_FOUND(HttpStatus.NOT_FOUND, "GENERAL_404", "존재하지 않는 엔티티입니다."),
    INSUFFICIENT_RIGHTS(HttpStatus.FORBIDDEN, "GENERAL_403", "권한이 없습니다."),



    // ORDER
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER_404", "존재하지 않는 주문입니다."),

    // member
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_409", "같은 이메일의 사용자가 있습니다."),
    PASSWORD_MISMATCH(HttpStatus.CONFLICT, "MEMBER_409", "비밀번호와 비밀번호 확인이 다릅니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_404", "같은 이메일의 회원을 찾을 수 없습니다."),
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "MEMBER_401", "이메일 혹은 비밀번호가 틀렸습니다."),
    SIGNATURE_EXCEPTION(HttpStatus.FORBIDDEN, "MEMBER_403", "토큰이 유효하지 않습니다."),
    MALFORMED_JWT(HttpStatus.FORBIDDEN, "MEMBER_403", "올바르지 않은 토큰입니다."),
    EXPIRED_JWT(HttpStatus.FORBIDDEN, "MEMBER_403", "토큰이 만료되었습니다. 다시 로그인 해주세요."),
    AUTH_FAILURE(HttpStatus.UNAUTHORIZED, "MEMBER_401", "인증에 실패했습니다."),
    NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "MEMBER_403", "허가되지 않은 요청입니다."),

    // PRODUCT
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRODUCT_404", "존재하지 않는 상품입니다."),

    // PAYMENT
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "PAYMENT_4O4", "존재하지 않는 결제입니다."),
    PAYMENT_UNCOMPLETED(HttpStatus.PAYMENT_REQUIRED, "PAYMENT_402", "결제가 진행되지 않았습니다."),
    PAYMENT_AMOUNT_MISMATCH(HttpStatus.BAD_REQUEST, "PAYMENT_400", "결제금액과 계산된 결제금액이 다릅니다."),

    // REVIEW
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW_404", "존재하지 않는 리뷰입니다."),

    // IMAGE
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "IMAGE_404", "존재하지 않는 사진입니다."),

    // CATEGORY
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY_404", "존재하지 않는 카테고리입니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
