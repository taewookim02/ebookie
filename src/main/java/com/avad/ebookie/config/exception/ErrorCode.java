package com.avad.ebookie.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EMAIL_ALREADY_EXISTS(406, "MEMBER_406", "같은 이메일의 사용자가 있습니다"),
    PASSWORD_MISMATCH(400, "MEMBER_400", "비번 확인 다름");

    private final int status;
    private final String code;
    private final String message;
}
