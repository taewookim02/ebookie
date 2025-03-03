package com.avad.ebookie.config.dataload;

import com.avad.ebookie.domain.author.entity.Author;
import com.avad.ebookie.domain.author.repository.AuthorDataLoader;
import com.avad.ebookie.domain.category.entity.Category;
import com.avad.ebookie.domain.category.repository.CategoryDataLoader;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.member.repository.MemberDataLoader;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.repository.ProductDataLoader;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import com.avad.ebookie.domain.product_author.repository.ProductAuthorDataLoader;
import com.avad.ebookie.domain.product_file.entity.ProductFile;
import com.avad.ebookie.domain.product_file.repository.ProductFileDataLoader;
import com.avad.ebookie.domain.product_image.entity.ProductImage;
import com.avad.ebookie.domain.product_image.repository.ProductImageDataLoader;
import com.avad.ebookie.domain.publisher.entity.Publisher;
import com.avad.ebookie.domain.publisher.repository.PublisherDataLoader;
import com.avad.ebookie.domain.review.entity.Review;
import com.avad.ebookie.domain.review.repository.ReviewDataLoader;
import com.avad.ebookie.domain.status.entity.Status;
import com.avad.ebookie.domain.status.repository.StatusDataLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DummyDataLoader {

    private final CategoryDataLoader categoryDataLoader;
    private final PublisherDataLoader publisherDataLoader;
    private final AuthorDataLoader authorDataLoader;
    private final ProductDataLoader productDataLoader;
    private final ProductAuthorDataLoader productAuthorDataLoader;
    private final MemberDataLoader memberDataLoader;
    private final ReviewDataLoader reviewDataLoader;
    private final ProductImageDataLoader productImageDataLoader;
    private final StatusDataLoader statusDataLoader;
    private final ProductFileDataLoader productFileDataLoader;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("DummyDataLoader.init");
        // 회원 더미데이터 생성 후 DB 저장
        List<Member> members = memberDataLoader.genereateMemberData();

        // 상태 더미데이터 생성 후 DB 저장
        List<Status> statuses = statusDataLoader.generateStatusData();

        // 카테고리 더미데이터 생성 후 DB 저장
        List<Category> categories = categoryDataLoader.generateCategoryData();

        // 출판사 더미데이터 생성 후 DB 저장
        List<Publisher> publishers = publisherDataLoader.generatePublisherData();

        // 저자 더미데이터 생성 후 DB 저장
        List<Author> authors = authorDataLoader.generateAuthorData();

        // 상품 생성 및 DB 저장
        List<Product> products = productDataLoader.generateProductData(categories, publishers);

        // 상품 이미지 생성 및 DB 저장
        List<ProductImage> productImages = productImageDataLoader.generateProductImageData(products);

        // 상품 파일 생성 및 DB 저장
        List<ProductFile> productFiles = productFileDataLoader.generateProductFileData(products);

        // 리뷰 데이터 생성 및 DB 저장
        List<Review> reviews = reviewDataLoader.generateReviewData(members, products);

        // 저자, 상품 맵핑 및 DB 저장
        List<ProductAuthor> productAuthors = productAuthorDataLoader.generateProductAuthorData(products, authors);


    }




}
