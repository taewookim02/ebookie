package com.avad.ebookie.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "이메일이 공백일 수 없습니다")
    @NotNull(message = "이메일이 null이어서는 안됩니다")
    @Email(message = "올바른 형식의 이메일 주소여야 합니다")
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
