package com.avad.ebookie.domain.member.repository;

import com.avad.ebookie.domain.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Auditing")
    void findMember() {
        // given
        Member member = Member.builder()
                .email("test@example.com")
                .password("1234")
                .build();

        // when
        Member savedMember = memberRepository.save(member);

        // then
        assertThat(savedMember.getCreatedAt()).isNotNull();
        assertThat(savedMember.getUpdatedAt()).isNotNull();

    }
}
