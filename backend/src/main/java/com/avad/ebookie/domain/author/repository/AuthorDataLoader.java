package com.avad.ebookie.domain.author.repository;

import com.avad.ebookie.domain.author.entity.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorDataLoader {
    private final AuthorRepository authorRepository;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("저자 더미데이터 체크");
        // 데이터 있는지 확인
        long count = authorRepository.count();
        if (count != 0) {
            log.info("저자 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return;
        }

        Map<String, String> defaultNameAndDesc = Map.ofEntries(
                entry("전호", "20년 차 백엔드 개발자로 네이버와 카카오에서 대규모 시스템 설계를 담당했습니다. Java와 Spring 프레임워크 전문가이며, MSA 설계 패턴에 대한 다수의 기술 서적을 집필했습니다."),
                entry("남건", "프론트엔드 개발자이자 UI/UX 전문가로, React와 Vue.js 프레임워크를 활용한 웹 애플리케이션 개발에 특화되어 있습니다. 현재 스타트업 CTO로 재직 중이며 웹 개발 강의를 진행하고 있습니다."),
                entry("정훈", "게임 엔진 전문가이며 유니티(Unity) 인증 강사입니다. 다수의 모바일 게임 출시 경험이 있으며, 게임 개발 입문자를 위한 온라인 강의를 운영하고 있습니다."),
                entry("박건", "AI 연구원으로 KAIST AI 대학원을 졸업하고 현재 네이버랩스에서 자연어 처리 모델 개발을 담당하고 있습니다. 머신러닝과 딥러닝 관련 강의를 다수 진행했습니다."),
                entry("류나영", "정보처리기사 출제위원으로 활동 중이며, IT 자격증 전문 강사입니다. 수많은 수강생들을 합격으로 이끈 경험을 바탕으로 실전 위주의 학습 서적을 집필하고 있습니다."),
                entry("추여진", "AWS 공인 솔루션스 아키텍트로 클라우드 인프라 설계 전문가입니다. DevOps와 클라우드 아키텍처에 관한 기술 블로그를 운영하며 다수의 기술 서적을 출간했습니다."),
                entry("박재인", "보안 전문가이자 화이트해커로 활동 중입니다. 정보보안기사 자격증 보유자이며, 웹 보안과 암호화에 관한 실무 중심의 교육 콘텐츠를 제작하고 있습니다."),
                entry("전나영", "프론트엔드 개발자이자 웹 접근성 전문가입니다. Angular와 TypeScript를 활용한 엔터프라이즈급 애플리케이션 개발 경험이 풍부하며, 웹 표준에 관한 저서를 출간했습니다."),
                entry("성광조", "오픈소스 컨트리뷰터이자 Python 백엔드 개발자입니다. Django와 FastAPI를 활용한 서버 개발에 전문성을 가지고 있으며, 코드 최적화와 성능 개선 관련 강의를 진행합니다."),
                entry("정철순", "게임 기획자 출신의 개발자로 언리얼 엔진 전문가입니다. AAA급 게임 개발 경험이 있으며, 게임 엔진 최적화와 그래픽스 프로그래밍 관련 강의를 진행합니다."),
                entry("한승헌", "머신러닝 엔지니어로 Google TensorFlow 인증 강사입니다. 컴퓨터 비전과 이미지 처리 분야에서 다년간의 실무 경험을 보유하고 있으며, AI 관련 온라인 교육 플랫폼을 운영합니다."),
                entry("서무열", "리눅스 커널 개발자이자 임베디드 시스템 전문가입니다. 삼성전자에서 시스템 프로그래밍 경험을 쌓았으며, 저수준 프로그래밍과 운영체제에 관한 강의를 진행합니다."),
                entry("전다미", "UI/UX 디자이너 출신의 프론트엔드 개발자로, 디자인 시스템 구축 전문가입니다. CSS 아키텍처와 모던 웹 디자인 패턴에 관한 다수의 기술 서적을 집필했습니다."),
                entry("신영혜", "데이터 사이언티스트이자 빅데이터 플랫폼 전문가입니다. 하둡과 스파크를 활용한 대규모 데이터 처리 경험이 풍부하며, 데이터 분석 관련 교육 콘텐츠를 제작합니다."),
                entry("최명우", "블록체인 개발자이자 스마트 컨트랙트 전문가입니다. 이더리움과 솔리디티를 활용한 DApp 개발 경험이 있으며, 블록체인 기술에 관한 입문서를 출간했습니다."),
                entry("예민욱", "모바일 앱 개발자로 Android와 iOS 플랫폼에서의 개발 경험이 풍부합니다. Flutter와 React Native를 활용한 크로스 플랫폼 개발 강의를 진행하며, 모바일 UI 패턴 관련 저서를 집필했습니다.")
        );

        // 엔티티 저장용 리스트
        List<Author> authorsToSave = new ArrayList<>();

        for (Map.Entry<String, String> entry : defaultNameAndDesc.entrySet()) {
            Author author = Author.builder()
                    .name(entry.getKey())
                    .description(entry.getValue())
                    .build();
            authorsToSave.add(author); // 저장용 리스트에 엔티티 추가
        }

        authorRepository.saveAll(authorsToSave); // db 저장
    }
}
