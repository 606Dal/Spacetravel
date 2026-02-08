package com.spacetravel.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

public class CaptchaGenerator {

	private static final int WIDTH = 150;
    private static final int HEIGHT = 50;
    private static final int CAPTCHA_LENGTH = 6;
	
	// 랜덤 문자 생성
    public static String generateCaptchaText() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captcha.append(chars.charAt(random.nextInt(chars.length())));
        }

        return captcha.toString();
    }
    
    // 캡차 이미지 생성 및 Base64 반환
    public static String generateCaptchaImage(String captchaText) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // 배경 색상 설정
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 텍스트 스타일 설정
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.BLACK);
        
        // 텍스트 랜덤 위치 적용
        Random random = new Random();
        for (int i = 0; i < captchaText.length(); i++) {
            int x = 20 + (i * 20);
            int y = 30 + random.nextInt(10);
            g.drawString(String.valueOf(captchaText.charAt(i)), x, y);
        }

        // 노이즈 추가
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }

        g.dispose();

        // 이미지 Base64로 변환
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", baos);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("캡차 생성 실패", e);
        }
    }
    
}
