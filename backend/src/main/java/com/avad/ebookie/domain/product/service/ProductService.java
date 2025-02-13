package com.avad.ebookie.domain.product.service;

import com.avad.ebookie.domain.product.dto.response.ProductDetailResponseDto;
import com.avad.ebookie.domain.product.mapper.ProductMapper;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDetailResponseDto> testProduct() {
        List<ProductDetailResponseDto> responses = productRepository.findAll().stream().map(product ->
                productMapper.toDto(product)
        ).collect(Collectors.toList());

        return responses;
    }
}
