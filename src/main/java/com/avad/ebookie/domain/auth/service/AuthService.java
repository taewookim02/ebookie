package com.avad.ebookie.domain.auth.service;

import com.avad.ebookie.domain.auth.dto.request.RegisterRequestDto;
import com.avad.ebookie.domain.auth.dto.response.AuthResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    public AuthResponseDto register(@Valid RegisterRequestDto registerRequestDto) {

    }
}
