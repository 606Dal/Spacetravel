package com.spacetravel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
			// nasa api 데이터 가져옴
			apodDTO = restClient.get()
					.uri(url)
					.retrieve()
					.body(ApodDTO.class);
			log.info("RequestID: {} - APOD REQUEST", requestID);
			
		} catch (Exception e) {
			log.warn("RequestID: {} - APOD REQUEST - Exception: Could not be retrieve APOD information from API", requestID, e.toString());
			return null;
		}
		
		return apodDTO;
	}

}
