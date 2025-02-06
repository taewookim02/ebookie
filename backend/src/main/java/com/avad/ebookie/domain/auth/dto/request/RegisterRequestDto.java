package com.avad.ebookie.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @NotBlank(message = "이메일이 공백일 수 없습니다")
    @NotNull(message = "이메일이 필요합니다")
    @Email(message = "올바른 형식의 이메일 주소여야 합니다")
    private String email;

    @NotNull(message = "비밀번호가 필요합니다")
    @NotBlank(message = "비밀번호가 공백일 수 없습니다")
    private String password;

    @NotNull(message = "비밀번호 확인이 필요합니다")
    @NotBlank(message = "비밀번호 확인이 공백일 수 없습니다")
    private String confirmPassword;
}
