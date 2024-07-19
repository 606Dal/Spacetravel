package com.spacetravel.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.service.BoardService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/board")
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
		
		return "index";
	}
	
	@GetMapping("/tpl")
	public String tplFragments() {
		
		return "tpl/tpl_fragments";
	}
	
	// 게시판 글 쓰기
	@GetMapping("/boardWriteForm")
	public String boardWriteForm() {
		log.info("...boardWriteForm() 호출...");
		
		//model.addAttribute("boardDTO", boardDTO);
		
		return "board/boardWriteForm";
	}
		// 글 db에 등록
	@PostMapping("/boardWriteOK")
	public String insertBoard(@Valid BoardDTO boardDTO, 
							  Model model) {
		
		try {
			
			boardService.insertBoard(boardDTO);
			
			model.addAttribute("msg", "글 등록에 성공하였습니다.");
			model.addAttribute("url", "/board/boardList");
			log.info(boardDTO.toString());
			
		} catch (Exception e) {
			
			model.addAttribute("msg", "글 등록에 실패하였습니다.");
			model.addAttribute("url", "/board/boardWriteForm");
			log.info(".....글 등록 실패....." + e.getMessage());
		}
		
		return "board/MessageAlert";
	}
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		
		List<BoardDTO> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		
		return "board/boardList";
	}
	
	
	
}
