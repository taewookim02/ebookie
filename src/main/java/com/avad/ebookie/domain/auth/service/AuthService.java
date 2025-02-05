package com.avad.ebookie.domain.auth.service;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.auth.dto.request.LoginRequestDto;
import com.avad.ebookie.domain.auth.dto.request.RegisterRequestDto;
import com.avad.ebookie.domain.auth.dto.response.AuthResponseDto;
import com.avad.ebookie.domain.auth.exception.EmailDuplicateException;
import com.avad.ebookie.domain.auth.exception.PasswordMismatchException;
import com.avad.ebookie.domain.auth.model.Token;
import com.avad.ebookie.domain.auth.model.TokenType;
import com.avad.ebookie.domain.auth.repository.TokenRepository;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Transactional
    public AuthResponseDto register(@Valid RegisterRequestDto request, HttpServletResponse response) {
        log.info("AuthService.register");

        // 유효성 검사
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
        saveMemberToken(savedMember, accessToken);
        // 리프레시 토큰 쿠키에 추가
        addRefreshTokenCookie(response, refreshToken);
        
        
        // 응답
        return AuthResponseDto
                .builder()
                .accessToken(accessToken).build();
    }

    private void addRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    private void saveMemberToken(Member savedMember, String accessToken) {
        Token token = Token.builder()
                .member(savedMember)
                .token(accessToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthResponseDto login(@Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
    }
}
