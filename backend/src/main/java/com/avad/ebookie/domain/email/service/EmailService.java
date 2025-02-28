package com.avad.ebookie.domain.email.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.password}")
    private String gPassword;

    public void forgotPassword(@NotBlank @Email String email, String randomPassword) {
        String title = "테스트: 임시 비밀번호 안내 이메일입니다.";
        String content = "임시 비밀번호: " + randomPassword;
        System.out.println("from = " + from);
        System.out.println("gPassword = " + gPassword);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(title);
        mailMessage.setText(content);
        mailMessage.setFrom(from);
        mailMessage.setReplyTo(from);

        mailSender.send(mailMessage);

    }
}
