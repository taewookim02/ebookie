package com.avad.ebookie.domain.review.repository;

import com.avad.ebookie.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
