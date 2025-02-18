package com.avad.ebookie.domain.cart.service;

import com.avad.ebookie.domain.cart.dto.request.BulkDeleteCartRequestDto;
import com.avad.ebookie.domain.cart.dto.request.CartAddRequestDto;
import com.avad.ebookie.domain.cart.dto.response.CartResponseDto;
import com.avad.ebookie.domain.cart.entity.Cart;
import com.avad.ebookie.domain.cart.mapper.CartMapper;
import com.avad.ebookie.domain.cart.repository.CartRepository;
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
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Transactional
    public String toggleCart(Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // 카트에 상품이 있는지 확인
        Cart cartProduct = cartRepository.findByProductIdAndMemberId(productId, loggedInMember.getId());

        // 카트에 추가할 상품
        Product product = productRepository.findById(productId).orElse(null);

        if (cartProduct == null) {
            addCart(product, loggedInMember);
            return "SAVED";
        } else {
            removeCart(product, loggedInMember);
            return "DELETED";
        }

    }

    private void removeCart(Product product, Member loggedInMember) {
        cartRepository.deleteByProductIdAndMemberId(product.getId(), loggedInMember.getId());
    }

    private void addCart(Product product, Member loggedInMember) {
        Cart build = Cart.builder()
                .member(loggedInMember)
                .product(product)
                .quantity(1)
                .build();
        cartRepository.save(build);
    }

    public void addToCart(CartAddRequestDto cartAddRequestDto) {
        System.out.println("cartAddRequestDto = " + cartAddRequestDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        // 이미 존재하는지 확인
        Cart existingCart = cartRepository.findByProductIdAndMemberId(cartAddRequestDto.getProductId(), loggedInMember.getId());

        // 존재 한다면 수량 + 1
        if (existingCart != null) {
            // eBook은 수량이 1로 고정이기에 수량 + 1을 하지 않음
            return;
//            existingCart.addItemQuantityByOne();
//            cartRepository.save(existingCart);
        } else {
            Product product = productRepository.findById(cartAddRequestDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("addToCart() product not found"));
            Cart newCart = Cart.builder()
                    .product(product)
                    .member(loggedInMember)
                    .quantity(1)
                    .build();
            cartRepository.save(newCart);
        }
    }

    @Transactional(readOnly = true)
    public List<CartResponseDto> getCartProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        List<Cart> cartList = cartRepository.findAllByMemberOrderByIdDesc(loggedInMember);

        return cartMapper.toDtoList(cartList);
    }

    @Transactional
    public void deleteCart(Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();
        cartRepository.deleteByProductIdAndMemberId(productId, loggedInMember.getId());
    }

    @Transactional
    public void bulkDeleteCart(BulkDeleteCartRequestDto bulkDeleteCartRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();
        cartRepository.deleteAllByMemberAndProductIdIn(loggedInMember, bulkDeleteCartRequestDto.getProductIds());
    }
}
