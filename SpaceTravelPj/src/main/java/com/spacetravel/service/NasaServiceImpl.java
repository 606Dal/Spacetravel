package com.spacetravel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.spacetravel.dto.ApodDTO;

@Service
public class NasaServiceImpl implements NasaService {
	
	@Value("${nasa.api.key}")
	private String apiKey;
	
	private final RestClient restClient;
	
	public NasaServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}
	
	private static final Logger log = LoggerFactory.getLogger(NasaServiceImpl.class);

	@Override
	public ApodDTO getApod(double requestID) {
		
		String url = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey;
		ApodDTO apodDTO = new ApodDTO();
		
		try {
			
			 ResponseEntity<ApodDTO> response = restClient.get()
					 .uri(url)
					 .retrieve()
					 .toEntity(ApodDTO.class);

			 apodDTO = response.getBody();
			 
			// 헤더에서 RateLimit 정보 추출
//			 HttpHeaders headers = response.getHeaders();
//		     String rateLimit = headers.getFirst("X-RateLimit-Limit");
//		     String rateRemaining = headers.getFirst("X-RateLimit-Remaining");

			
		} catch (Exception e) {
			log.warn("RequestID: {} - APOD REQUEST - Exception: Could not be retrieve APOD information from API", requestID);
			log.warn("Exception Message: {}", e.getMessage());
			return null;
		}
		
		return apodDTO;
	}
	
}
