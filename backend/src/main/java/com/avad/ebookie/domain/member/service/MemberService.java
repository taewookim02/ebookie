package com.avad.ebookie.domain.member.service;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.auth.exception.PasswordMismatchException;
import com.avad.ebookie.domain.auth.exception.UnauthorizedAccessException;
import com.avad.ebookie.domain.auth.service.JwtService;
import com.avad.ebookie.domain.member.dto.request.MemberEditRequestDto;
import com.avad.ebookie.domain.member.dto.response.EditDetailResponseDto;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public EditDetailResponseDto update(MemberEditRequestDto memberEditRequestDto, String userEmailFromJwt) throws PasswordMismatchException, UnauthorizedAccessException {
        // 비밀번호, 비밀번호 확인 검사
        if (!memberEditRequestDto.getNewPassword().equals(memberEditRequestDto.getNewPasswordConfirm())) {
            throw new PasswordMismatchException("비밀번호와 비밀번호 확인이 다릅니다.", ErrorCode.PASSWORD_MISMATCH);
        }

        // JWT토큰과 아이디 일치 검사
        if (!memberEditRequestDto.getEmail().equals(userEmailFromJwt)) {
            throw new UnauthorizedAccessException(ErrorCode.NOT_AUTHORIZED);
        }

        // 현재 이메일, 비밀번호 일치 검사 및 인증 객체 추출
        // 이메일, 비밀번호 틀렸을 시 BadCrendentialsException
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        memberEditRequestDto.getEmail(),
                        memberEditRequestDto.getCurrentPassword()
                )
        );

        Member member = (Member) authenticate.getPrincipal();
        System.out.println("member = " + member.getPassword());
        member.updatePassword(passwordEncoder.encode(memberEditRequestDto.getNewPassword()));
        System.out.println("member.getPassword() = " + member.getPassword());
        memberRepository.save(member);
        return null;
    }
}
