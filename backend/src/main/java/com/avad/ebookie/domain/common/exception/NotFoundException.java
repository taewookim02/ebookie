package com.avad.ebookie.domain.common.exception;

import com.avad.ebookie.config.exception.ErrorCode;

public class NotFoundException extends BaseException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
