package com.avad.ebookie.domain.publisher.repository;

import com.avad.ebookie.config.event.PublisherDataLoadedEvent;
import com.avad.ebookie.domain.publisher.entity.Publisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublisherDataLoader {
    private final PublisherRepository publisherRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    @EventListener(ApplicationReadyEvent.class) // 스프링 컨테이너 모두 띄워졌을 때
    public void init() {
        log.info("출판사 더미데이터 체크");
        // 데이터 있는지 확인
        long count = publisherRepository.count();
        if (count != 0) {
            log.info("출판사 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return;
        }

        String[] defaultData = {
                "한빛미디어",      // IT 전문 서적으로 유명한 출판사
                "위키북스",        // 프로그래밍 언어 관련 서적 전문
                "길벗",           // IT 자격증 및 실무 서적
                "제이펍",         // 해외 IT 서적 번역 전문
                "인사이트",       // 소프트웨어 개발 방법론 전문
                "에이콘출판사",    // 프로그래밍 심화 서적
                "프리렉",         // 신기술 트렌드 도서
                "루비페이퍼",      // 게임 개발 전문
                "책만",          // 실용적인 프로그래밍 서적
                "영진닷컴",       // IT 자격증 수험서 전문
                "이지스퍼블리싱",  // 입문자 위한 IT 서적
                "디지털북스",      // AI와 빅데이터 전문
                "BJ퍼블릭",       // 모바일 앱 개발 전문
                "성안당",         // 컴퓨터 공학 전문
                "렉토리안",       // 블록체인과 암호화폐 전문
                "패스트캠퍼스북스" // 실무 프로젝트 중심 서적
        };

        List<Publisher> publishersToSave = new ArrayList<>(); // 엔티티 저장용

        for (String data : defaultData) {
            Publisher publisher = Publisher.builder()
                    .name(data)
                    .build();
            publishersToSave.add(publisher);
        }

        publisherRepository.saveAll(publishersToSave);

        // 데이터 로드 완료 이벤트, 상품 더미데이터 넣을 때 사용
        applicationEventPublisher.publishEvent(new PublisherDataLoadedEvent(this));
    }
}
