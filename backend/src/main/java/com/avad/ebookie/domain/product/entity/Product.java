package com.avad.ebookie.domain.product.entity;

import com.avad.ebookie.domain.publisher.entity.Publisher;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128)
    private String name;

    private Long price;


    // 출판사
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false) // optional=false JPA 런타임 조건
    @JoinColumn(name = "publisher_id"
            , nullable = false) // nullable=false DDL 조건
    @JsonBackReference // json 재귀 에러 해결
    private Publisher publisher;
}
