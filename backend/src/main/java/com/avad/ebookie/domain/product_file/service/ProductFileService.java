package com.avad.ebookie.domain.product_file.service;

import com.avad.ebookie.domain.member.entity.Member;
import com.avad.ebookie.domain.order.repository.OrderRepository;
import com.avad.ebookie.domain.order_detail.repository.OrderDetailRepository;
import com.avad.ebookie.domain.product.entity.Product;
import com.avad.ebookie.domain.product.repository.ProductRepository;
import com.avad.ebookie.domain.product_file.dto.response.ProductFileResponseDto;
import com.avad.ebookie.domain.product_file.entity.ProductFile;
import com.avad.ebookie.domain.product_file.mapper.ProductFileMapper;
import com.avad.ebookie.domain.product_file.repository.ProductFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ProductFileService {
    private final ProductFileRepository productFileRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductFileMapper productFileMapper;


    @Transactional
    public List<ProductFileResponseDto> getProductFiles(Long productId) {
        // get member
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member loggedInMember = (Member) authentication.getPrincipal();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product not found: " + productId));

        List<ProductFile> files = product.getFiles();

        orderDetailRepository.incrementDownloadCount(loggedInMember.getId(), productId);

        return productFileMapper.toDtoList(files);
    }

    public ByteArrayOutputStream getByteArrayOutputStream(List<ProductFileResponseDto> files) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (ProductFileResponseDto file : files) {
                UrlResource resource = new UrlResource("file:" + file.getFullPath());
                ZipEntry zipEntry = new ZipEntry(file.getFileName());
                zos.putNextEntry(zipEntry);

                StreamUtils.copy(resource.getInputStream(), zos);
                zos.closeEntry();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return baos;
    }
}
