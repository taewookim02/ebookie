package com.avad.ebookie.domain.product_image.service;

import com.avad.ebookie.config.exception.ErrorCode;
import com.avad.ebookie.domain.common.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {

    @Value("${app.upload.img-dir}")
    private String filePath;

    public Resource returnImage(String imageName) {
        try {
            // TODO: is this optimal?
            String fullPath = filePath + imageName;
            return new FileSystemResource(fullPath);
        } catch (Exception e) {
            throw new NotFoundException(ErrorCode.IMAGE_NOT_FOUND);
        }
    }
}
