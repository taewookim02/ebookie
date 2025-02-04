package com.avad.ebookie.domain.auth.exception;

import com.avad.ebookie.config.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class EmailDuplicateException extends RuntimeException {
    private ErrorCode errorCode;

    public EmailDuplicateException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}
