package com.avad.ebookie.domain.product.entity;

import com.avad.ebookie.domain.category.entity.Category;
import com.avad.ebookie.domain.publisher.entity.Publisher;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @JoinColumn(name = "publisher_id", nullable = false) // nullable=false DDL 조건
    @JsonBackReference // json 변환 시 publisher 미포함
    private Publisher publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
