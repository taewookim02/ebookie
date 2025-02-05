package com.avad.ebookie.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EMAIL_ALREADY_EXISTS(406, "MEMBER_406", "같은 이메일의 사용자가 있습니다"),
    PASSWORD_MISMATCH(400, "MEMBER_400", "비번 확인 다름"),
    MEMBER_NOT_FOUND(404, "MEMBER_404", "같은 이메일의 회원을 찾을 수 없습니다"),
    BAD_CREDENTIALS(401, "MEMBER_401", "이메일 혹은 비밀번호가 틀렸습니다"),
    AUTH_BEARER_INVALID(403, "MEMBER_403", "토큰이 없거나 유효하지 않습니다");

    private final int status;
    private final String code;
    private final String message;
}
