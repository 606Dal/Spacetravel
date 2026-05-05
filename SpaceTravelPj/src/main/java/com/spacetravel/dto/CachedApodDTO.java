package com.spacetravel.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CachedApodDTO {
	
	private Long id;
    private String title;
    private String explanation;		// 본문
    private String copyright;
    private String localPath;   	// 내 서버에 저장된 경로
    private String originalUrl; 	// 원본 NASA 이미지 URL
    private String originalHdUrl; 	// NASA HD 이미지 원본 링크 (저장시 다운로드 안 함)
    private String mediaType;
    private String apodDate;
    private LocalDateTime createdAt;
    
	@Override
	public String toString() {
		return "CachedApodDTO [id=" + id + ", title=" + title + ", copyright=" + copyright + ", localPath=" + localPath
				+ ", originalUrl=" + originalUrl + ", originalHdUrl=" + originalHdUrl + ", mediaType=" + mediaType
				+ ", apodDate=" + apodDate + ", createdAt=" + createdAt + "]";
	}


}
