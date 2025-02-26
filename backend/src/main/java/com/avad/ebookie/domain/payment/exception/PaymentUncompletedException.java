package com.avad.ebookie.domain.payment.exception;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.common.exception.BaseException;

public class PaymentUncompletedException extends BaseException {
    public PaymentUncompletedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
