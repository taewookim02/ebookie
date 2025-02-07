package com.avad.ebookie.domain.member.controller;

import com.avad.ebookie.domain.auth.service.JwtService;
import com.avad.ebookie.domain.member.dto.response.EditDetailResponseDto;
import com.avad.ebookie.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // TODO: 비밀번호 변경
    
    // TODO: 회원정보 수정
    @GetMapping("detail")
    public ResponseEntity<EditDetailResponseDto> detail(HttpServletRequest request) {
        // 요청에서 토큰 구하기
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7); // "Bearer " 제외

        String userEmail = jwtService.extractUsername(jwt); // jwt에서 유저이메일 구하기
//        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail); // 유저 정보 더 필요할 시
        EditDetailResponseDto detailResponseDto = EditDetailResponseDto.builder()
                .email(userEmail)
                .build();
        return ResponseEntity.ok(detailResponseDto);
    }


    // TODO: 비밀번호 찾기
}
