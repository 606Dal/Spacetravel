package com.spacetravel.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CacheManageService {
	
	@CacheEvict(value = "todayApodCache", allEntries = true)
	public void clearApodCache() {
        log.info("NASA APOD 캐시가 수동으로 초기화되었습니다.");
    }

}
