package com.avad.ebookie.domain.common.exception;

import com.avad.ebookie.config.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
