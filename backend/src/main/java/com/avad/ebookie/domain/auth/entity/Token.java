package com.avad.ebookie.domain.auth.entity;

import com.avad.ebookie.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
}

/*
* create table token (
        expired bit not null,
        revoked bit not null,
        id bigint not null auto_increment,
        member_id bigint,
        token varchar(255),
        token_type enum ('BEARER'),
        primary key (id)
    ) engine=InnoDB
* */