package com.avad.ebookie.domain.product.entity;

import com.avad.ebookie.domain.cart.entity.Cart;
import com.avad.ebookie.domain.category.entity.Category;
import com.avad.ebookie.domain.common.entity.BaseTimeEntity;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import com.avad.ebookie.domain.product_file.entity.ProductFile;
import com.avad.ebookie.domain.product_image.entity.ProductImage;
import com.avad.ebookie.domain.publisher.entity.Publisher;
import com.avad.ebookie.domain.review.entity.Review;
import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128)
    private String name;

    private Double price;

    @Lob
    private String description;

    private Double discountRate;

    private LocalDate publishedDate;

    @Min(0)
    private Long sold = 0L;

    // 출판사
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false) // optional=false JPA 런타임 조건
    @JoinColumn(name = "publisher_id", nullable = false) // nullable=false DDL 조건
    @JsonBackReference // json 변환 시 publisher 미포함
    private Publisher publisher;


    // 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    // 저자
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductAuthor> authors = new ArrayList<>();

    // 리뷰목록
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    // 이미지 목록
    @OneToMany(mappedBy = "parentProduct", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductImage> images = new ArrayList<>();

    // 파일 목록
    @OneToMany(mappedBy = "parentProduct", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProductFile> files = new ArrayList<>();

    // 찜하기 목록
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SavedProduct> saved = new ArrayList<>();

    // 카트 목록
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cart> cartList = new ArrayList<>();


    // 리뷰 평균 평점 구하기
    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        return reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
    }
}
