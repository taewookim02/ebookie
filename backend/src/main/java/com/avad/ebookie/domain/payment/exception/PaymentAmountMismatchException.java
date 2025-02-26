package com.avad.ebookie.domain.payment.exception;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.common.exception.BaseException;

public class PaymentAmountMismatchException extends BaseException {
  public PaymentAmountMismatchException(ErrorCode errorCode) {
    super(errorCode);
  }
}
