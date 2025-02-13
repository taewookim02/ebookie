package com.avad.ebookie.domain.publisher.controller;

import com.avad.ebookie.domain.publisher.dto.response.PublisherResponseDto;
import com.avad.ebookie.domain.publisher.entity.Publisher;
import com.avad.ebookie.domain.publisher.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherRepository publisherRepository;

    @GetMapping("test")
    public ResponseEntity<List<PublisherResponseDto>> test() {
        List<PublisherResponseDto> response = publisherRepository.findAll().stream().map(publisher ->
                PublisherResponseDto.toDto(publisher)
        ).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
