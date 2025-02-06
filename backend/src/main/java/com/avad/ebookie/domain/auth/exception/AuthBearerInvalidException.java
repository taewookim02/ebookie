package com.avad.ebookie.domain.auth.exception;

import com.avad.ebookie.config.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AuthBearerInvalidException extends RuntimeException {
    private ErrorCode errorCode;
    public AuthBearerInvalidException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
