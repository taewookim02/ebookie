package com.avad.ebookie.domain.auth.service;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.auth.dto.request.LoginRequestDto;
import com.avad.ebookie.domain.auth.dto.request.RegisterRequestDto;
import com.avad.ebookie.domain.auth.dto.response.AuthResponseDto;
import com.avad.ebookie.domain.auth.exception.EmailDuplicateException;
import com.avad.ebookie.domain.auth.exception.MemberNotFoundException;
import com.avad.ebookie.domain.auth.exception.PasswordMismatchException;
import com.avad.ebookie.domain.auth.model.Token;
import com.avad.ebookie.domain.auth.model.TokenType;
import com.avad.ebookie.domain.auth.repository.TokenRepository;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.member.entity.Role;
import com.avad.ebookie.domain.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


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
                .role(Role.MEMBER)
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

    @Transactional
    public AuthResponseDto login(@Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        log.info("AuthService.login");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        Member member = memberRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        // jwt 토큰 생성
        String accessToken = jwtService.generateToken(member);
        String refreshToken = jwtService.generateRefreshToken(member);

        // 기존 토큰 만료
        revokeAllMemberTokens(member);
        // 새로운 토큰 발급
        saveMemberToken(member, accessToken);
        // 쿠키에 리프레시토큰 추가
        addRefreshTokenCookie(response, refreshToken);

        // 응답
        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }

    private void revokeAllMemberTokens(Member member) {
        List<Token> validMemberTokens = tokenRepository.findAllValidTokensByMember(member.getId());
        if (validMemberTokens.isEmpty()) {
            return;
        }

        validMemberTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validMemberTokens);
    }

    public AuthResponseDto refreshToken(HttpServletRequest request, HttpServletResponse response) {
        //  request 쿠키에서 리프레시 토큰 추출
        final String refreshToken = extractRefreshTokenFromCookie(request);
        if (refreshToken == null) {
            return null;
        }
        log.info("refreshToken: ", refreshToken);

        // 리프레시 토큰에서 유저이메일 추출
        final String userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            // 이메일로 유저정보 추출
            Member userDetails = memberRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new MemberNotFoundException(ErrorCode.MEMBER_NOT_FOUND));
            
            // 리프레시 토큰이 유효할 경우 
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                // 새로운 accessToken 발급
                String accessToken = jwtService.generateToken(userDetails);
                
                // 존재했던 토큰 만료
                revokeAllMemberTokens(userDetails);
                // 새로 발급된 accessToken 저장
                saveMemberToken(userDetails, accessToken);

                // 응답
                AuthResponseDto authResponseDto = AuthResponseDto.builder()
                        .accessToken(accessToken)
                        .build();
                return authResponseDto;
            }
        }
        // 응답
        return null;
    }

    private String extractRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh_token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
