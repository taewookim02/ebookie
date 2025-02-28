package com.avad.ebookie.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class ForgotPasswordRequestDto {

    @NotBlank
    @Email
    private String email;
}
