package com.spacetravel.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomImageUtils {
	
	/**
     * 외부 URL의 이미지를 다운로드하여 서버에 저장.
     */
    public String saveImageFromUrl(String imageUrl, String uploadDir, String date) {
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        String extension = imageUrl.substring(imageUrl.lastIndexOf("."));
        if (!extension.matches("\\.(jpg|jpeg|png|gif)")) {
            extension = ".jpg"; // 기본값
        }
        
        String savedName = "apod_" + date + extension;
        File saveFile = new File(uploadDir, savedName);

        try {
            URL url = new URL(imageUrl);
            try (InputStream in = url.openStream()) {
                // 스트림을 읽어 파일로 복사 (이미 있으면 덮어쓰기)
                Files.copy(in, saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            
            // 필요하면 여기서 썸네일 생성 메서드(createThumbnail)를 호출
            
        } catch (IOException e) {
            log.error("URL 이미지 저장 중 오류 발생: {}", imageUrl, e);
            throw new RuntimeException("외부 이미지 저장 실패: " + imageUrl, e);
        }

        return savedName;
    }

    // 이미지 하나만 저장할 때
    public String saveImageFile(MultipartFile file, String uploadDir) {
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs(); // 디렉토리 없으면 생성
        }

        // 파일이 비어있으면
        if (file.isEmpty()) {
        	throw new IllegalArgumentException("업로드된 파일이 비어 있습니다.");
        }

        String contentType = file.getContentType();

        // 이미지 타입 확인
        if (contentType == null || !contentType.startsWith("image")) {
        	throw new IllegalArgumentException("지원하지 않는 파일 형식입니다. 이미지 파일만 업로드 가능합니다: " + contentType);
        }

        String originalName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String savedName = uuid + "_" + originalName;

        File saveFile = new File(uploadDir, savedName);

        try {
            // 실제 파일 저장
            file.transferTo(saveFile);

        } catch (IOException e) {
        	throw new RuntimeException("파일 저장 중 시스템 오류 발생: " + savedName, e);
        }

        return savedName;
    }

    // 기존 파일을 다른 경로로 이동
    public String moveImageFile(String fileName, String fromDir, String toDir) {
        File sourceFile = new File(fromDir, fileName);
        File targetDir = new File(toDir);

        if (!sourceFile.exists()) {
        	throw new IllegalArgumentException("이동할 원본 이미지 파일을 찾을 수 없습니다: " + fileName);
        }

        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File targetFile = new File(targetDir, fileName);

        try {
            Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.delete(sourceFile.toPath());

            // 이동된 파일 기반으로 썸네일 생성
//            File thumbnailFile = new File(targetDir, "s_" + fileName);
//            createThumbnail(targetFile, thumbnailFile);

        } catch (IOException e) {
        	throw new RuntimeException("파일 복사/삭제 중 시스템 오류 발생: " + fileName, e);
        }

        return fileName; // 이동된 파일명 그대로 반환
    }

    public void deleteImageFile(String fileName, String dir) {

        if (fileName == null || fileName.isBlank()) {
            return;
        }

        File file = new File(dir, fileName);

        try {
            if (file.exists()) {
                file.delete();
            }

            File thumbnail = new File(dir, "s_" + fileName);
            if (thumbnail.exists()) {
                thumbnail.delete();
            }

        } catch (Exception e) {
            log.warn("파일 삭제 실패: {}", fileName, e);
        }
    }

//    private void createThumbnail(File source, File target) {
//        try {
//            Thumbnails.of(source)
//                    .size(200, 200)
//                    .toFile(target);
//        } catch (IOException e) {
//            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR, "썸네일 생성 실패: " + source.getName());
//        }
//    }
}
