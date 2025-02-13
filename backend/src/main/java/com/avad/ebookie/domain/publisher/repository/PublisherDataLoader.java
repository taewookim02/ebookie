package com.avad.ebookie.domain.publisher.repository;

import com.avad.ebookie.domain.publisher.entity.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublisherDataLoader {
    private final PublisherRepository publisherRepository;

    public List<Publisher> generatePublisherData() {
        log.info("출판사 더미데이터 체크");
        // 데이터 있는지 확인
        long count = publisherRepository.count();
        if (count != 0) {
            log.info("출판사 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }

        // 출판사 더미
        String[] defaultData = {
                "한빛미디어",
                "위키북스",
                "길벗",
                "제이펍",
                "인사이트",
                "에이콘출판사",
                "프리렉",
                "루비페이퍼",
                "책만",
                "영진닷컴",
                "이지스퍼블리싱",
                "디지털북스",
                "BJ퍼블릭",
                "성안당",
                "렉토리안",
                "패스트캠퍼스북스"
        };

        List<Publisher> publishersToSave = new ArrayList<>(); // 엔티티 저장용

        for (String data : defaultData) {
            Publisher publisher = Publisher.builder()
                    .name(data)
                    .build();
            publishersToSave.add(publisher);
        }

        return publisherRepository.saveAll(publishersToSave);
    }
}
