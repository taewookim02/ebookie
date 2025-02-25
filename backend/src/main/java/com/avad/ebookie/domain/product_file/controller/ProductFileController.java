package com.avad.ebookie.domain.product_file.controller;

import com.avad.ebookie.domain.product_file.dto.response.ProductFileResponseDto;
import com.avad.ebookie.domain.product_file.service.ProductFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductFileController {
    private final ProductFileService productFileService;

    @GetMapping(value = "/products/files/download/{productId}", produces = "application/zip")
    public ResponseEntity<byte[]> downloadProductFile(@PathVariable("productId") Long productId) throws IOException {
        // body
        List<ProductFileResponseDto> files = productFileService.getProductFiles(productId);
        ByteArrayOutputStream baos = productFileService.getByteArrayOutputStream(files);

        // header
        String zipFileName = "files.zip";
        String encodedFileName = UriUtils.encode(zipFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .header(HttpHeaders.CONTENT_TYPE, "application/zip")
                .body(baos.toByteArray());
    }
}
