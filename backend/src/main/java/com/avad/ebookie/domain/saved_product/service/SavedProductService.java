package com.avad.ebookie.domain.saved_product.service;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.saved_product.entity.SavedProduct;
import com.avad.ebookie.domain.saved_product.repository.SavedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SavedProductService {
    private final ProductRepository productRepository;
    private final SavedProductRepository savedProductRepository;

    @Transactional
    public String toggleSave(Long productId) {
        // 로그인한 유저 구해오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // 찜 이력이 있는지 확인
        SavedProduct savedProduct = savedProductRepository.findByProductIdAndMemberId(productId, loggedInMember.getId());

        Product product = productRepository.findById(productId).orElse(null);

        // 찜 이력이 있다면 저장
        if (savedProduct == null) {
            addSave(product, loggedInMember);
            return "SAVED";
        } else {
            // 찜 이력이 없다면 삭제
            removeSave(product, loggedInMember);
            return "DELETED";
        }

    }

    private void removeSave(Product product, Member loggedInMember) {
        savedProductRepository.deleteByProductIdAndMemberId(product.getId(), loggedInMember.getId());
    }

    private void addSave(Product product, Member member) {
        SavedProduct savedProduct = SavedProduct.builder()
                .product(product)
                .member(member)
                .build();
        savedProductRepository.save(savedProduct);
    }
}
