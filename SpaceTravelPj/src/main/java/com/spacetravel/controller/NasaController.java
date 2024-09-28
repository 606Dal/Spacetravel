package com.spacetravel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spacetravel.dto.ApodDTO;
import com.spacetravel.service.NasaServiceImpl;

@Controller
@RequestMapping("/infoview")
public class NasaController {
	
	private final NasaServiceImpl nasaServiceImpl;
	
	public NasaController(NasaServiceImpl nasaServiceImpl) {
		this.nasaServiceImpl = nasaServiceImpl;
	}
	
	@GetMapping("/nasaApod")
	public String nasaApod(Model model) {
		
		double requestID = Math.floor((Math.random() * 1000)*100) / 100;
		// Nasa api Apod 요청
		ApodDTO apodDTO = nasaServiceImpl.getApod(requestID);
		
		if(apodDTO != null) {
			model.addAttribute("apodDTO", apodDTO);
		}
		else {
			model.addAttribute("msg", "정보를 가져오지 못했습니다.");
			model.addAttribute("url", "/");

			return "board/messageAlert";
		}
		
		return "infoview/nasaApod";
	}
	
	@PostMapping("/nasaApodFullimage")
	public String nasaApodFullimage(Model model, @ModelAttribute("apodDTO") ApodDTO apodDTO) {
		
		if(apodDTO == null) {
			model.addAttribute("msg", "정보를 가져오지 못했습니다.");
			model.addAttribute("url", "/");

			return "board/messageAlert";
		}
		return "infoview/nasaApodFullimage";
	}

}
