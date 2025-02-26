package com.avad.ebookie.domain.order.exception;

import com.avad.ebookie.config.exception.ErrorCode;
import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException {
    private ErrorCode errorCode;
    public OrderNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
