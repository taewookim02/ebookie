package com.avad.ebookie.domain.auth.exception;

import com.avad.ebookie.config.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UnauthorizedAccessException extends RuntimeException {
    private ErrorCode errorCode;
    public UnauthorizedAccessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
