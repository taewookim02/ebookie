package com.avad.ebookie.domain.auth.service;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.auth.dto.request.RegisterRequestDto;
import com.avad.ebookie.domain.auth.dto.response.AuthResponseDto;
import com.avad.ebookie.domain.auth.exception.EmailDuplicateException;
import com.avad.ebookie.domain.auth.exception.PasswordMismatchException;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.member.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public AuthResponseDto register(@Valid RegisterRequestDto request) {
        log.info("AuthService.register");


        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("비밀번호와 비밀번호 확인이 다릅니다.", ErrorCode.PASSWORD_MISMATCH);
        }

        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailDuplicateException("이메일 중복", ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        // 멤버 저장
        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        Member savedMember = memberRepository.save(member);

        // 토큰 생성
        String accessToken = jwtService.generateToken(savedMember);
        String refreshToken = jwtService.generateRefreshToken(savedMember);
        log.info(accessToken);
        log.info(refreshToken);
        
        
        // 토큰 저장
        
        
        // 응답
        return AuthResponseDto
                .builder()
                .accessToken(accessToken).build();
    }
}
