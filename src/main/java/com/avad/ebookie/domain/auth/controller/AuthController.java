package com.avad.ebookie.domain.auth.controller;

import com.avad.ebookie.domain.auth.dto.request.RegisterRequestDto;
import com.avad.ebookie.domain.auth.dto.request.LoginRequestDto;
import com.avad.ebookie.domain.auth.dto.response.AuthResponseDto;
import com.avad.ebookie.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        log.info(registerRequestDto.toString());
        return ResponseEntity.ok(authService.register(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        log.info(loginRequestDto.toString());
        return ResponseEntity.ok().build();
    }


}
