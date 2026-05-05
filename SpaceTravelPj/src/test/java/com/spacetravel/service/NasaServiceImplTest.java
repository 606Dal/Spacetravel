package com.spacetravel.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.spacetravel.dto.ApodDTO;
import com.spacetravel.dto.CachedApodDTO;
import com.spacetravel.mapper.ApodMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
public class NasaServiceImplTest {
	
	@Autowired
    private NasaService nasaService;

    @Autowired
    private ApodMapper apodMapper;

    @Value("${file.upload.apod-images-dir}")
    private String apodImagesDir;

    @Test
    @DisplayName("관리자 캐시 저장 로직: 이미지 다운로드 및 DB 인서트 확인")
    public void testSaveApodToCache() {
        // Given: 가짜 ApodDTO 준비 (항상 다운로드 가능한 임시 이미지 사용)
        String testDate = "2026-05-05";
        ApodDTO dummyApod = new ApodDTO(
                "테스트 저작권",
                testDate,
                "테스트 설명입니다.",
                "https://placehold.co/300.jpg", // 확실히 다운로드되는 임시 이미지 URL
                "image",
                "테스트 제목",
                "https://placehold.co/300.jpg" 
        );

        // When: 서비스의 저장 로직 실행
        nasaService.saveApodToCache(dummyApod);

        // Then 1: DB에 잘 들어갔는지 확인
        CachedApodDTO savedDto = apodMapper.selectApodByDate(testDate);
        assertThat(savedDto).isNotNull();
        assertThat(savedDto.getTitle()).isEqualTo("테스트 제목");
        log.info("▶️ DB 인서트 검증 완료: {}", savedDto.getLocalPath());

        // Then 2: 물리적 파일이 진짜 C드라이브에 생성되었는지 확인
        File downloadedImage = new File(apodImagesDir, savedDto.getLocalPath());
        assertThat(downloadedImage.exists()).isTrue(); // 파일이 존재해야 성공!
        log.info("▶️ 실제 파일 다운로드 검증 완료: {}", downloadedImage.getAbsolutePath());
        
        // (선택) 테스트가 끝난 후 만들어진 찌꺼기 파일 삭제
        if(downloadedImage.exists()) {
            downloadedImage.delete();
            log.info("▶️ 테스트용 이미지 파일 삭제 완료");
        }
    }


}
