package com.spacetravel.service;

import org.springframework.stereotype.Service;

import com.spacetravel.dto.ApodDTO;

@Service
public interface NasaService {

	ApodDTO getApod(double requestID); // 오늘의 천문학 사진을 가져옴
	
	String saveApodToCache(ApodDTO apodDTO);
}
