package com.spacetravel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	/*
	 * 테스트 확인 용 main, tpl
	 */
	@GetMapping("/main")
	public String main() {
		log.info("...main() 호출...");
		
		return "include/index";
	}
	
	@GetMapping("/tpl")
	public String tplFragments() {
		
		return "tpl/tpl_fragments";
	}
	
	// 게시판 글 쓰기
	@GetMapping("/boardWriteForm")
	public String boardWriteForm() {
		log.info("...boardWriteForm() 호출...");
		
		return "board/boardWriteForm";
	}
		// 글 db에 등록
	@PostMapping("/boardWriteOK")
	public String insertBoard(BoardDTO b1) {
		
		try {
			boardService.insertBoard(b1);
			log.info(b1.toString());
			log.info("...글 등록 성공...");
			
		} catch (Exception e) {
			log.info(".....글 등록 실패....." + e.getMessage());
		}
		
		return "redirect:/boardWriteForm";
	}
	
	
	
}
