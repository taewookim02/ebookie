package com.avad.ebookie.domain.product_image.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {

    @Value("${app.upload.dir}")
    private String filePath;

    public Resource returnImage(String imageName) {
        try {
            // TODO: is this optimal?
            String fullPath = filePath + imageName;
            return new FileSystemResource(fullPath);
        } catch (Exception e) {
            // TODO: handle FileNotFoundException
            throw new RuntimeException(e);
        }
    }
}
