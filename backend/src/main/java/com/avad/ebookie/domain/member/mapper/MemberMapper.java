package com.avad.ebookie.domain.member.mapper;

import com.avad.ebookie.domain.member.dto.response.MemberInfoResponseDto;
import com.avad.ebookie.domain.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberMapper {

    public MemberInfoResponseDto toDto(Member entity) {
        return MemberInfoResponseDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .role(entity.getRole())
                .build();
    }
}
