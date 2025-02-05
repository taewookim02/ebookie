package com.avad.ebookie.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = false)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService; // 유저 찾기에 필요한 서비스
    private final PasswordEncoder passwordEncoder;



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


        String[] allowedPaths = { "/", "/auth/**" };
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(allowedPaths)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .authenticationProvider(authProvider) // 인증하려면 authProvider 필요
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
