package com.spacetravel.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spacetravel.dto.ApodDTO;
import com.spacetravel.service.NasaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/infoview")
@Slf4j
public class NasaController {
	
	private final NasaService nasaService;
	
	public NasaController(NasaService nasaService) {
		this.nasaService = nasaService;
	}
	
	@GetMapping("/nasaApod")
	public String nasaApod(Model model) {
		
		double requestID = Math.floor((Math.random() * 1000)*100) / 100;
		// Nasa api Apod 요청(만약 오류가 생기면 db에서 예전 자료 가져와서 보여줌)
		ApodDTO apodDTO = nasaService.getApod(requestID);
		
		if(apodDTO != null) {
			if(apodDTO.getApodCopyright() == null) {
				apodDTO.setApodCopyright("null");
			}
			model.addAttribute("apodDTO", apodDTO);
			
			return "infoview/nasaApod";
			
		} else {
			model.addAttribute("msg", "우주 정거장과 통신이 끊겼습니다.");
			model.addAttribute("url", "/");

			return "board/messageAlert";
		}
		
	}
	
	@PostMapping("/nasaApodFullimage")
	public String nasaApodFullimage(Model model, @ModelAttribute("apodDTO") ApodDTO apodDTO) {
		
		if(apodDTO == null || apodDTO.getApodHdurl() == null) {
			model.addAttribute("msg", "고화질 이미지 정보를 가져오지 못했습니다.");
			model.addAttribute("url", "/infoview/nasaApod");

			return "board/messageAlert";
		}
		return "infoview/nasaApodFullimage";
	}
	
	@ResponseBody
	@PostMapping("/saveApodCache")
	public ResponseEntity<Map<String, String>> saveApodCache(@RequestBody ApodDTO apodDTO) {
		
		// Service 실행 후 결과 상태값 받기 (예: "SUCCESS", "NOT_IMAGE" 등)
	    String resultStatus = nasaService.saveApodToCache(apodDTO);
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("status", resultStatus);
	    
	    return ResponseEntity.ok(response);
	}

}
