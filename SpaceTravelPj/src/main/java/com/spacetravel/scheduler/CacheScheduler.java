package com.spacetravel.scheduler;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class CacheScheduler {

    // 초 분 시 일 월 요일.
    // 매일 14시 10분 00초에 실행.
    @CacheEvict(value = "todayApodCache", allEntries = true)
    @Scheduled(cron = "0 10 14 * * *", zone = "Asia/Seoul")
    public void autoClearApodCache() {
        log.info("오후 2시 10분(KST) NASA 데이터 업데이트 주기에 맞춰 캐시를 자동 초기화합니다.");
    }
}
