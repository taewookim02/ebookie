package com.avad.ebookie.domain.auth.exception;


import com.avad.ebookie.config.exception.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordMismatchException extends RuntimeException {
    private ErrorCode errorCode;

    public PasswordMismatchException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
