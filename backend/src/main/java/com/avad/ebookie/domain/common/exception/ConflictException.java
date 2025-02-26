package com.avad.ebookie.domain.common.exception;

import com.avad.ebookie.config.exception.ErrorCode;

public class ConflictException extends BaseException {
    public ConflictException(ErrorCode errorCode) {
        super(errorCode);
    }
}
