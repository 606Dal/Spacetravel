package com.spacetravel.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*
 * 컨트롤러에서 오류 발생 시 로그 출력 후 메시지 알림창으로 이동
 */
@ControllerAdvice
public class BindExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(BindExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String handleValidException(
			MethodArgumentNotValidException ex,
			Model model) {
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult()
			.getFieldErrors()
			.forEach(error -> 
				errors.put(error.getField(), error.getDefaultMessage()));
		
		model.addAttribute("msg", "오류가 발생하였습니다.");
		model.addAttribute("url", "/");
		log.warn(errors.toString());
		
		return "board/messageAlert";
	}
	
}
