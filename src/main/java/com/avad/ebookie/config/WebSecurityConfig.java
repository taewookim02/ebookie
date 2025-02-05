package com.avad.ebookie.config;

import com.avad.ebookie.config.exception.JwtAuthenticationEntryPoint;
import com.avad.ebookie.config.filter.JwtAuthenticationFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService; // 유저 찾기에 필요한 서비스
    private final PasswordEncoder passwordEncoder; // Bcrypt 인코더
    private final JwtAuthenticationFilter jwtAuthFilter; // JWT 필터
    private final LogoutHandler logoutHandler; // LogoutService implements LogoutHandler
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; //

    /*
     * 1. 보호할 URL 선언하기
     * 2. jwtfilter 추가하기
     *
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); // 인증하려면 authProvider 필요
        authProvider.setUserDetailsService(userDetailsService); // 유저찾을 곳 설정
        authProvider.setPasswordEncoder(passwordEncoder); // 비밀번호 인코더 설정


        String[] allowedPaths = {"/", "/auth/**"};
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf 사용 x (REST)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(allowedPaths)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션에 정보저장 x
                )
                .authenticationProvider(authProvider) // 인증하려면 authProvider 필요
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // jwt 필터
                .logout((logoutConfig) -> {
                    logoutConfig.logoutUrl("/auth/logout");
                    logoutConfig.addLogoutHandler(logoutHandler); // LogoutService implements LogoutHandler
                    logoutConfig.logoutSuccessHandler(((request, response, authentication) -> {
                        clearRefreshTokenCookie(response);
                        response.setStatus(HttpServletResponse.SC_OK);
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("로그아웃 성공");
                    }));
                })
                .exceptionHandling(exceptionConfig ->
                        exceptionConfig.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                );
        return http.build();
    }

    // refresh_token 덮어쓰기
    private void clearRefreshTokenCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        response.addCookie(cookie);
    }

}
