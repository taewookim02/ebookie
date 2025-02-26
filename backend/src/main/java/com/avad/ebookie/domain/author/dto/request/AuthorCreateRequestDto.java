package com.avad.ebookie.domain.author.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCreateRequestDto {
    private String name;
    private String description;
}
