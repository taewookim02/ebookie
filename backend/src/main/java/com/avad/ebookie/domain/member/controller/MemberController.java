package com.avad.ebookie.domain.member.controller;

import com.avad.ebookie.domain.auth.service.JwtService;
import com.avad.ebookie.domain.member.dto.request.MemberEditRequestDto;
import com.avad.ebookie.domain.member.dto.response.EditDetailResponseDto;
import com.avad.ebookie.domain.member.dto.response.MemberInfoResponseDto;
import com.avad.ebookie.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> info() {
        MemberInfoResponseDto memberInfoResponseDto = memberService.info();
        return ResponseEntity.ok(memberInfoResponseDto);
    }

    // TODO: 회원정보 수정
    @PatchMapping("/update")
    public ResponseEntity<EditDetailResponseDto> update(@Valid @RequestBody MemberEditRequestDto memberEditRequestDto, HttpServletRequest request) {
        System.out.println("memberEditRequestDto = " + memberEditRequestDto);
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);

        String userEmail = jwtService.extractUsername(jwt);
        System.out.println("userEmail = " + userEmail);

        EditDetailResponseDto editDetailResponseDto = memberService.update(memberEditRequestDto, userEmail);

        return null;
    }

    // 유저 정보
    @GetMapping("/detail")
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
