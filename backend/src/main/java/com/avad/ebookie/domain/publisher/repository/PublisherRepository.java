package com.avad.ebookie.domain.publisher.repository;

import com.avad.ebookie.domain.publisher.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
