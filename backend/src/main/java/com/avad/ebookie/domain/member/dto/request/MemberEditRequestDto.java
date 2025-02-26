package com.avad.ebookie.domain.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEditRequestDto {
    @NotBlank(message = "이메일이 공백일 수 없습니다")
    @NotNull(message = "이메일이 null이어서는 안됩니다")
    @Email(message = "올바른 형식의 이메일 주소여야 합니다")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, message = "비밀번호는 최소 8글자 입니다")
    @Size(max = 16, message = "비밀번호는 최대 16글자 입니다")
    private String newPassword;

    @NotNull
    @NotBlank
    private String newPasswordConfirm;

    @NotNull
    @NotBlank
    private String currentPassword;
}
