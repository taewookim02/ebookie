package com.avad.ebookie.domain.auth.controller;

import com.avad.ebookie.domain.auth.dto.request.RegisterRequestDto;
import com.avad.ebookie.domain.auth.dto.request.LoginRequestDto;
import com.avad.ebookie.domain.auth.dto.response.AuthResponseDto;
import com.avad.ebookie.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping("/register")
    public String register() {
        return "/auth/register.html";
    }


    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto, HttpServletResponse response) {
        log.info(registerRequestDto.toString());
        return ResponseEntity.ok(authService.register(registerRequestDto, response));
    }

    @GetMapping("/login")
    public String login() {
        return "/auth/login.html";
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        log.info(loginRequestDto.toString());
        return ResponseEntity.ok(authService.login(loginRequestDto, response));
    }

    @ResponseBody
    @GetMapping("/refresh-token")
    public ResponseEntity<AuthResponseDto> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(authService.refreshToken(request, response));
    }


}
