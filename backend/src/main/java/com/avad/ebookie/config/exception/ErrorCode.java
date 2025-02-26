package com.avad.ebookie.config.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // GENERAL
    NOT_FOUND(HttpStatus.NOT_FOUND, "GENERAL_404", "존재하지 않는 엔티티입니다."),


    // member
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_409", "같은 이메일의 사용자가 있습니다."),
    PASSWORD_MISMATCH(HttpStatus.CONFLICT, "MEMBER_409", "비밀번호와 비밀번호 확인이 다릅니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_404", "같은 이메일의 회원을 찾을 수 없습니다."),
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "MEMBER_401", "이메일 혹은 비밀번호가 틀렸습니다."),
    SIGNATURE_EXCEPTION(HttpStatus.FORBIDDEN, "MEMBER_403", "토큰이 유효하지 않습니다."),
    MALFORMED_JWT(HttpStatus.FORBIDDEN, "MEMBER_403", "올바르지 않은 토큰입니다."),
    EXPIRED_JWT(HttpStatus.FORBIDDEN, "MEMBER_403", "토큰이 만료되었습니다. 다시 로그인 해주세요."),
    AUTH_FAILURE(HttpStatus.UNAUTHORIZED, "MEMBER_401", "인증에 실패했습니다."),
    NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "MEMBER_403", "허가되지 않은 요청입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
