package com.avad.ebookie.domain.product_author.repository;


import com.avad.ebookie.domain.author.entity.Author;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product_author.entity.ProductAuthor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductAuthorDataLoader {
    private final ProductAuthorRepository productAuthorRepository;

    public List<ProductAuthor> generateProductAuthorData(List<Product> products, List<Author> authors) {
        long count = productAuthorRepository.count();
        if (count != 0) {
            log.info("상품, 저자 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }

        List<ProductAuthor> productAuthorsToSave = new ArrayList<>();
        Random random = new Random();

        for (Product product : products) {
            // 각 책마다 1~3명의 저자를 랜덤하게 할당
            int authorCount = random.nextInt(3) + 1;
            List<Integer> selectedAuthorIndices = new ArrayList<>();

            for (int i = 0; i < authorCount; i++) {
                int authorIndex;
                do {
                    authorIndex = random.nextInt(authors.size());
                } while (selectedAuthorIndices.contains(authorIndex));

                selectedAuthorIndices.add(authorIndex);
                Author author = authors.get(authorIndex);

                ProductAuthor productAuthor = ProductAuthor.builder()
                        .product(product)
                        .author(author)
                        .authorOrder(i + 1)
                        .build();
                productAuthorsToSave.add(productAuthor);
            }
        }

        log.info("상품저자 {} 개 생성 완료", productAuthorsToSave.size());
        return productAuthorRepository.saveAll(productAuthorsToSave);


    }
}
