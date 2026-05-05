package com.spacetravel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.spacetravel.dto.CachedApodDTO;
import com.spacetravel.mapper.ApodMapper;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
@Transactional
public class ApodMapperTest {
	
	@Autowired
	private ApodMapper apodMapper;
	
	@Disabled
	@Test
	@DisplayName("1. 캐시 데이터 정상 저장 테스트")
	public void testInsertCachedApod() {
		// Given (테스트 데이터 준비)
		CachedApodDTO dto = CachedApodDTO.builder()
				.title("테스트 은하수 사진")
				.explanation("본문 생략") // DB에는 들어가지만 로그에선 빠짐
				.localPath("/images/static/apod_test.jpg")
				.originalUrl("https://nasa.gov/test.jpg")
				.apodDate("2026-05-01") 
				.build();

		// When (실제 매퍼 실행)
		int result = apodMapper.insertCachedApod(dto);

		// Then (검증)
		assertThat(result).isEqualTo(1); // 1건이 성공적으로 insert 되었는지 확인
		log.info("▶️ 저장 테스트 성공: {}", dto);
	}

	@Disabled
	@Test
	@DisplayName("2. 날짜를 이용한 단건 조회 테스트")
	public void testSelectApodByDate() {
		// Given (먼저 데이터를 하나 넣습니다)
		String testDate = "2026-05-02";
		CachedApodDTO dummy = CachedApodDTO.builder()
				.title("날짜 조회용 데이터")
				.explanation("본문 생략")
				.localPath("/images/static/date_test.jpg")
				.apodDate(testDate)
				.build();
		apodMapper.insertCachedApod(dummy);

		// When (방금 넣은 날짜로 조회)
		CachedApodDTO resultDTO = apodMapper.selectApodByDate(testDate);

		// Then (null이 아니고, 제목이 일치하는지 검증)
		assertThat(resultDTO).isNotNull();
		assertThat(resultDTO.getTitle()).isEqualTo("날짜 조회용 데이터");
		log.info("▶️ 날짜 조회 성공: {}", resultDTO);
	}

	@Disabled
	@Test
	@DisplayName("3. 랜덤 조회 테스트 (Fallback용)")
	public void testSelectRandomApod() {
		// Given (랜덤으로 뽑힐 데이터 최소 2개 인서트)
		apodMapper.insertCachedApod(CachedApodDTO.builder().title("랜덤 1").localPath("path1").apodDate("2026-05-03").build());
		apodMapper.insertCachedApod(CachedApodDTO.builder().title("랜덤 2").localPath("path2").apodDate("2026-05-04").build());

		// When
		CachedApodDTO randomDTO = apodMapper.selectRandomApod();

		// Then
		assertThat(randomDTO).isNotNull();
		log.info("▶️ 랜덤 조회 성공 (아무거나 1건): {}", randomDTO);
	}

}
