package com.avad.ebookie.domain.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ForgotPasswordResponseDto {
    private String message;
    private String email;
}
