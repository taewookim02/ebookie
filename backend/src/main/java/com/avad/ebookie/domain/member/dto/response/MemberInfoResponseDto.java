package com.avad.ebookie.domain.member.dto.response;

import com.avad.ebookie.domain.member.entity.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoResponseDto {
    private Long id;
    private String email;
    private Enum<Role> role;
}
