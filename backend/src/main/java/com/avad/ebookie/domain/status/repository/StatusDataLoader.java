package com.avad.ebookie.domain.status.repository;

import com.avad.ebookie.domain.status.entity.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatusDataLoader {
    private final StatusRepository statusRepository;

    public List<Status> generateStatusData() {
        long count = statusRepository.count();
        if (count != 0) {
            log.info("상태 데이터가 존재하니 더미데이터를 넣지 않습니다. 개수: " + count);
            return new ArrayList<>();
        }

        // 엔티티 저장용 리스트
        List<Status> statusesToSave = new ArrayList<>();
        String[] defaultData = {"신규주문", "구매확정", "취소요청", "반품요청"};
        for (String name : defaultData) {
            Status status = Status.builder()
                    .name(name)
                    .build();
            statusesToSave.add(status);
        }

        return statusRepository.saveAll(statusesToSave);
    }
}
