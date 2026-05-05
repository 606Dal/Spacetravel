package com.spacetravel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spacetravel.dto.CachedApodDTO;

@Mapper
public interface ApodMapper {
	// 1. 캐시된 데이터 저장
    int insertCachedApod(CachedApodDTO cachedApodDTO);

    // 2. 특정 날짜의 데이터가 이미 있는지 확인 (중복 저장 방지용)
    CachedApodDTO selectApodByDate(@Param("apodDate") String apodDate);

    // 3. NASA API 오류 시 보여줄 랜덤 데이터
    CachedApodDTO selectRandomApod();
}
