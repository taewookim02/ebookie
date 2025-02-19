package com.avad.ebookie.domain.status.repository;

import com.avad.ebookie.domain.status.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findByName(String name);
}
