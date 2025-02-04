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

    @Transactional
    public AuthResponseDto register(@Valid RegisterRequestDto request) {
        log.info("AuthService.register");


        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("비밀번호와 비밀번호 확인이 다릅니다.", ErrorCode.PASSWORD_MISMATCH);
        }

        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailDuplicateException("이메일 중복", ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        memberRepository.save(member);

        return null;
    }
}
