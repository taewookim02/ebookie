package com.avad.ebookie.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EMAIL_ALREADY_EXISTS(406, "MEMBER_406", "같은 이메일의 사용자가 있습니다."),
    PASSWORD_MISMATCH(400, "MEMBER_400", "비밀번호와 비밀번호 확인이 다릅니다."),
    MEMBER_NOT_FOUND(404, "MEMBER_404", "같은 이메일의 회원을 찾을 수 없습니다."),
    BAD_CREDENTIALS(401, "MEMBER_401", "이메일 혹은 비밀번호가 틀렸습니다."),
    SIGNATURE_EXCEPTION(403, "MEMBER_403", "토큰이 유효하지 않습니다."),
    MALFORMED_JWT(403, "MEMBER_403", "올바르지 않은 토큰입니다."),
    EXPIRED_JWT(403, "MEMBER_403", "토큰이 만료되었습니다. 다시 로그인 해주세요."),
    AUTH_FAILURE(401, "MEMBER_401", "인증에 실패했습니다."),
    NOT_AUTHORIZED(403, "MEMBER_403", "허가되지 않은 요청입니다.");

    private final int status;
    private final String code;
    private final String message;
}
