package com.avad.ebookie.config.filter;

import com.avad.ebookie.domain.auth.repository.TokenRepository;
import com.avad.ebookie.domain.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
            // throw new AuthBearerInvalidException(ErrorCode.AUTH_BEARER_INVALID); // the response is not being sent
        }
        jwt = authHeader.substring(7); // "Bearer " 제외
        userEmail = jwtService.extractUsername(jwt); // 토큰에서 유저 이메일 구하기

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 유저 이메일로 유저 객체 구하기
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

            // 토큰 만료 확인 // TODO: Caused by: org.hibernate.NonUniqueResultException: Query did not return a unique result: 2 results were returned
            Boolean isTokenValid = tokenRepository.findFirstByTokenOrderByIdDesc(jwt)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);

            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 시큐리티 컨텍스트에 로그인 정보 저장
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
