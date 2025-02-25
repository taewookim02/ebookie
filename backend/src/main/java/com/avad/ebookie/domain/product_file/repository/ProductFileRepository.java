package com.avad.ebookie.domain.product_file.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avad.ebookie.domain.product_file.entity.ProductFile;

public interface ProductFileRepository extends JpaRepository<ProductFile, Long> {

}
