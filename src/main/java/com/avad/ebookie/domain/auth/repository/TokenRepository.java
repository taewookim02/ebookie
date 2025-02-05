package com.avad.ebookie.domain.auth.repository;


import com.avad.ebookie.domain.auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
