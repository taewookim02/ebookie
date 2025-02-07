package com.avad.ebookie.domain.member.repository;

import com.avad.ebookie.domain.member.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(@NotBlank(message = "이메일이 공백일 수 없습니다") @NotNull(message = "이메일이 필요합니다") @Email(message = "올바른 형식의 이메일 주소여야 합니다") String email);
}
