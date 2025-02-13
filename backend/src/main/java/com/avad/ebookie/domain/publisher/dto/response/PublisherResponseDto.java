package com.avad.ebookie.domain.publisher.dto.response;

import com.avad.ebookie.domain.publisher.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherResponseDto {
    private Long id;
    private String name;

    public static PublisherResponseDto toDto(Publisher entity) {
        return PublisherResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
