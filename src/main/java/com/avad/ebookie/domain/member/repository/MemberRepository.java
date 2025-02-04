package com.avad.ebookie.domain.member.repository;

import com.avad.ebookie.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
