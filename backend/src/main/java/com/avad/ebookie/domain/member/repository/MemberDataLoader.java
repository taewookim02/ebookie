package com.avad.ebookie.domain.member.repository;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.member.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberDataLoader {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Member> genereateMemberData() {
        log.info("멤버 더미데이터 체크");

        long count = memberRepository.count();
        if (count != 0) {
            log.info("상품 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }

        Faker faker = new Faker(new Locale("us")); // 더미데이터 생성용
        List<Member> membersToSave = new ArrayList<>();
        // 10개의 계정
        for (int i = 0; i < 10; i++) {
            // 아이디, 비밀번호
            String email = (faker.name().firstName() + "." + faker.name().lastName()).toLowerCase() + "@gmail.com";
            String password = "12341234!@"; // 기본 비밀번호
            // 비밀번호 해싱
            String encodedPassword = passwordEncoder.encode(password);

            // 멤버 엔티티 만들기
            Member member = Member.builder()
                    .email(email)
                    .password(encodedPassword)
                    .role(i == 9 ? Role.ADMIN : Role.MEMBER) // 마지막 유저는 ADMIN
                    .isDeleted(i == 6) // 7번째 유저는 삭제됨
                    .build();
            membersToSave.add(member);
        }
        // TEMP:
        Member testMember = Member.builder()
                .email("test@example.com")
                .password(passwordEncoder.encode("12341234"))
                .role(Role.ADMIN) // 마지막 유저는 ADMIN
                .isDeleted(false) // 7번째 유저는 삭제됨
                .build();
        membersToSave.add(testMember);

        return memberRepository.saveAll(membersToSave);
    }
}
