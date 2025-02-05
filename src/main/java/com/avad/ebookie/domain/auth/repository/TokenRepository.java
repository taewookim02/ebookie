package com.avad.ebookie.domain.auth.repository;


import com.avad.ebookie.domain.auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("""
            select t from Token t inner join Member m on t.member.id = m.id
            where m.id = :memberId and (t.expired = false and t.revoked = false)
            """)
    List<Token> findAllValidTokensByMember(@Param("memberId") Long memberId);

    Optional<Token> findByToken(String jwt);
}
