package com.avad.ebookie.domain.author.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private Long id;
    private String name;
    private String description;
}
