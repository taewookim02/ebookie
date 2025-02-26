package com.avad.ebookie.domain.publisher.entity;

import com.avad.ebookie.domain.product.entity.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Publisher { // 출판사
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64)
    private String name;

    @OneToMany(mappedBy = "publisher"
            , cascade = CascadeType.ALL
            , orphanRemoval = true
            , fetch = FetchType.LAZY) // why does publisherRepository.findAll() still get all the products?
    @JsonManagedReference // json 재귀 에러 해결
    private List<Product> products = new ArrayList<>();

}
