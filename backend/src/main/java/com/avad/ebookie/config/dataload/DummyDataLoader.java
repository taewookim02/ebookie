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
import com.avad.ebookie.domain.publisher.entity.Publisher;
import com.avad.ebookie.domain.publisher.repository.PublisherDataLoader;
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

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("DummyDataLoader.init");
        // 회원 더미데이터 생성 후 DB 저장
        List<Member> members = memberDataLoader.genereateMemberData();

        // 카테고리 더미데이터 생성 후 DB 저장
        List<Category> categories = categoryDataLoader.generateCategoryData();

        // 출판사 더미데이터 생성 후 DB 저장
        List<Publisher> publishers = publisherDataLoader.generatePublisherData();

        // 저자 더미데이터 생성 후 DB 저장
        List<Author> authors = authorDataLoader.generateAuthorData();

        // 출판사 데이터 생성 후 상품 생성 및 DB 저장
        List<Product> products = productDataLoader.generateProductData(categories, publishers);

        // 저자, 상품 맵핑 및 DB 저장
        List<ProductAuthor> productAuthors = productAuthorDataLoader.generateProductAuthorData(products, authors);

    }




}
