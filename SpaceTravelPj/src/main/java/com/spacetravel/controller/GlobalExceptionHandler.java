package com.spacetravel.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;
/*
 * 컨트롤러에서 오류 발생 시 로그 출력 후 메시지 알림창으로 이동
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected String handleValidException(MethodArgumentNotValidException ex
										, Model model) {
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
	@ExceptionHandler(ConstraintViolationException.class)
	protected String handleConstraintValidException(ConstraintViolationException e
												  , Model model) {
		
		model.addAttribute("msg", "오류가 발생하였습니다.");
		model.addAttribute("url", "/");
		log.warn("새 비밀번호 입력 오류");
		
		return "board/messageAlert";
	}
	
	// reply에 숫자 외의 문자가 들어올 때 예외처리
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected String handleMethodArgumentTypeMismatchException(
											  MethodArgumentTypeMismatchException e
											, Model model) {
		
		model.addAttribute("msg", "오류가 발생하였습니다.");
		model.addAttribute("url", "/");
		log.warn(e.getMessage());
		
		return "board/messageAlert";
	}
	
}
