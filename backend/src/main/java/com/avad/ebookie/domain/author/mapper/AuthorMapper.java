package com.avad.ebookie.domain.author.mapper;

import com.avad.ebookie.domain.author.dto.response.AuthorResponseDto;
import com.avad.ebookie.domain.author.entity.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorMapper {

    public AuthorResponseDto toDto(Author entity) {
        return AuthorResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
