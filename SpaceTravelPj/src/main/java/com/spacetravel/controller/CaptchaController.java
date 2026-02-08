package com.spacetravel.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spacetravel.util.CaptchaGenerator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class CaptchaController {

	@GetMapping("/captcha")
    @ResponseBody
    public Map<String, String> getCaptcha(HttpServletRequest request) {
        String captchaText = CaptchaGenerator.generateCaptchaText();
        
        request.getSession().setAttribute("captcha", captchaText);
        
        String captchaImage = CaptchaGenerator.generateCaptchaImage(captchaText);
        
        Map<String, String> response = new HashMap<>();
        response.put("captcha", "data:image/png;base64," + captchaImage);
        return response;
    }
}
