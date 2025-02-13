package com.avad.ebookie.domain.author.repository;

import com.avad.ebookie.domain.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
