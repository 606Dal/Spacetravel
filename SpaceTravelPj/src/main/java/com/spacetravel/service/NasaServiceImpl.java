package com.spacetravel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.spacetravel.dto.ApodDTO;
import com.spacetravel.dto.CachedApodDTO;
import com.spacetravel.mapper.ApodMapper;
import com.spacetravel.util.CustomImageUtils;

@Service
public class NasaServiceImpl implements NasaService {
	
	@Value("${nasa.api.key}")
	private String apiKey;
	
	@Value("${file.upload.apod-images-dir}")
	private String apodImagesDir;
	
	@Value("${file.display.prefix}")
    private String displayPrefix;
	
	private final RestClient restClient;
	private final ApodMapper apodMapper;
	private final CustomImageUtils imageUtils;
	
	private static final Logger log = LoggerFactory.getLogger(NasaServiceImpl.class);
	
	public NasaServiceImpl(RestClient restClient, ApodMapper apodMapper, CustomImageUtils imageUtils) {
		this.restClient = restClient;
		this.apodMapper = apodMapper;
		this.imageUtils = imageUtils;
	}
	

	@Override
	@Cacheable(value = "todayApodCache", key = "T(java.time.LocalDate).now().toString()")
	public ApodDTO getApod(double requestID) {
		
		log.info("[Cache Miss] 메모리에 데이터가 없습니다. NASA API를 직접 호출합니다.");
		
		String url = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey;
		
		try {
			
			 ResponseEntity<ApodDTO> response = restClient.get()
					 .uri(url)
					 .retrieve()
					 .toEntity(ApodDTO.class);

			 // 헤더에서 RateLimit 정보 추출
			 HttpHeaders headers = response.getHeaders();
//			 String rateLimit = headers.getFirst("X-RateLimit-Limit");
			 String rateRemaining = headers.getFirst("X-RateLimit-Remaining");
			 
			 log.debug("NASA API 남은 호출 횟수: {}", rateRemaining);
			 
			 return response.getBody();
			 
		} catch (Exception e) {
			log.warn("RequestID: {} - NASA API 장애 발생. 예전 사진을 랜덤하게 가져옴.", requestID);
			
			CachedApodDTO cachedDto = apodMapper.selectRandomApod();
			
			log.debug("▶️ DB 랜덤조회 결과 - cachedDto: {}", cachedDto);
			
			if (cachedDto != null) {
                
				ApodDTO fallbackDto = new ApodDTO();
				fallbackDto.setApodTitle(cachedDto.getTitle());
				fallbackDto.setApodExplanation(cachedDto.getExplanation());
				fallbackDto.setApodCopyright(cachedDto.getCopyright());
				fallbackDto.setApodDate(cachedDto.getApodDate());
				fallbackDto.setApodUrl(displayPrefix + cachedDto.getLocalPath()); 
				fallbackDto.setApodHdurl(cachedDto.getOriginalHdUrl());
				fallbackDto.setApodMedia_type(cachedDto.getMediaType());
				
				log.debug("fallbackDto: {}", fallbackDto);
				
				return fallbackDto;
			}
			
			return null;
			
		}
		
	}


	/**
     * 관리자가 '저장' 버튼을 눌렀을 때 실행될 캐싱 메서드
     */
	@Override
	public String saveApodToCache(ApodDTO apodDTO) {
		
		// 미디어 타입 체크: 이미지가 아니면 즉시 종료
		if (!"image".equals(apodDTO.getApodMedia_type())) {
			log.info("미디어 타입이 이미지가 아닙니다 ({}). 파일 다운로드 및 DB 저장을 생략합니다.", apodDTO.getApodMedia_type());
			return "NOT_IMAGE";
		}
		
		// 이미 저장된 날짜인지 확인 (중복 방지)
        if (apodMapper.selectApodByDate(apodDTO.getApodDate()) != null) {
            log.info("이미 저장된 날짜입니다: {}", apodDTO.getApodDate());
            return "DUPLICATE";
        }

        // 이미지 다운로드 및 물리적 저장
        String savedFileName = imageUtils.saveImageFromUrl(
                apodDTO.getApodUrl(), 
                apodImagesDir, 
                apodDTO.getApodDate()
        );

        CachedApodDTO cachedDTO = CachedApodDTO.builder()
                .title(apodDTO.getApodTitle())
                .explanation(apodDTO.getApodExplanation())
                .copyright(apodDTO.getApodCopyright())
                .localPath(savedFileName) // 파일명만 저장하거나, 상대 경로 저장
                .originalUrl(apodDTO.getApodUrl())
                .originalHdUrl(apodDTO.getApodHdurl())
                .mediaType(apodDTO.getApodMedia_type())
                .apodDate(apodDTO.getApodDate())
                .build();
                
        apodMapper.insertCachedApod(cachedDTO);
        log.debug("APOD 내용 저장 완료: {}", savedFileName);
        
        return "SUCCESS";
		
	}
	
}
