package com.avad.ebookie.domain.common.exception;

import com.avad.ebookie.config.exception.ErrorCode;

public class ForbiddenException extends BaseException {
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
