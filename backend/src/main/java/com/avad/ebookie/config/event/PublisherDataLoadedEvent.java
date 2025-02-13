package com.avad.ebookie.config.event;

import org.springframework.context.ApplicationEvent;

// 더미데이터 로딩용 커스텀 이벤트
public class PublisherDataLoadedEvent extends ApplicationEvent {
    public PublisherDataLoadedEvent(Object source) {
        super(source);
    }
}
