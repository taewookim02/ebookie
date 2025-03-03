package com.avad.ebookie.domain.liked_product.service;


import com.avad.ebookie.domain.liked_product.dto.request.BulkDeleteLikeRequestDto;
import com.avad.ebookie.domain.liked_product.dto.response.LikedProductResponseDto;
import com.avad.ebookie.domain.liked_product.entity.LikedProduct;
import com.avad.ebookie.domain.liked_product.mapper.LikedProductMapper;
import com.avad.ebookie.domain.liked_product.repository.LikedProductRepository;
import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikedProductService {

    private final LikedProductRepository likedProductRepository;
    private final ProductRepository productRepository;
    private final LikedProductMapper likedProductMapper;
    
    @Transactional
    public String toggleLike(Long productId) {
        // 로그인한 유저 구해오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // 좋아요 이력이 있는지 확인
        LikedProduct likedProduct = likedProductRepository.findByProductIdAndMemberId(productId, loggedInMember.getId());
        // 좋아요 할 상품 get
        Product product = productRepository.findById(productId).orElse(null);

        if (likedProduct == null) {
            addLike(product, loggedInMember);
            return "SAVED";
        } else {
            removeLike(product, loggedInMember);
            return "DELETED";
        }


    }

    private void removeLike(Product product, Member loggedInMember) {
        likedProductRepository.deleteByProductIdAndMemberId(product.getId(), loggedInMember.getId());
    }

    private void addLike(Product product, Member loggedInMember) {
        LikedProduct build = LikedProduct.builder()
                .member(loggedInMember)
                .product(product)
                .build();
        likedProductRepository.save(build);
    }

    @Transactional(readOnly = true)
    public List<LikedProductResponseDto> getLikedProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        List<LikedProduct> likedProducts = likedProductRepository.findAllByMemberIdOrderByIdDesc(loggedInMember.getId());

        List<LikedProductResponseDto> likedProductResponseDtos = likedProductMapper.toDtoList(likedProducts);

        return likedProductResponseDtos;


    }

    @Transactional
    public void deleteLike(Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();
        likedProductRepository.deleteByProductIdAndMemberId(productId, loggedInMember.getId());
    }

    @Transactional
    public void bulkDeleteLikes(BulkDeleteLikeRequestDto bulkDeleteLikeRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();
        likedProductRepository.deleteAllByMemberAndProductIdIn(loggedInMember, bulkDeleteLikeRequestDto.getProductIds());
    }
}
