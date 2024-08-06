package com.spacetravel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.dto.FindCreteriaDTO;
import com.spacetravel.dto.PageCriteriaDTO;
import com.spacetravel.dto.PagingDTO;
import com.spacetravel.service.BoardService;
import com.spacetravel.service.CustomUserDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	/*
	 * 테스트 확인 용 main
	 */
	@GetMapping("/main")
	public String main() {
		log.info("...main() 호출...");
		
		return "include/index";
	}
	
	// 게시판 글 쓰기
	@GetMapping("/boardWriteForm")
	public void boardWriteForm() {
	}
		// 글 db에 등록
	@PostMapping("/boardWriteOK")
	public String insertBoard(@Valid BoardDTO boardDTO
							, Authentication authentication
							, Model model) {
		
		try {
			boardDTO.setWriter(authentication.getName());
			boardService.insertBoard(boardDTO);
			
			model.addAttribute("msg", "글 등록에 성공하였습니다.");
			model.addAttribute("url", "/board/boardList?page=1&numPerPage=10");
			
		} catch (Exception e) {
			
			model.addAttribute("msg", "글 등록에 실패하였습니다.");
			model.addAttribute("url", "/board/boardWriteForm");
			log.info("글 등록 중 오류 발생");
		}
		
		return "board/messageAlert";
	}
	
	// 글 목록 가져오기
/*	@GetMapping("/boardList")
	public String boardList(Model model) {
		
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "board/boardList";
	}
*/	
	// 글 내용 보기
	@GetMapping("/boardReadPage")
	public String boardReadPage(@RequestParam(value = "id", required = false) Integer id
							  , Model model) {
		if(id != null) {
			BoardDTO boardDTO = boardService.readBoard(id);
			// db에서 가져온 정보가 없을 경우
			if(boardDTO == null) {
				model.addAttribute("msg", "존재하지 않는 글입니다.");
				model.addAttribute("url", "/board/boardList?page=1&numPerPage=10");
				
				return "board/messageAlert";
			}
			model.addAttribute("boardDTO", boardDTO);
		} else {
			// id가 null일 때 빈 객체 전송
			model.addAttribute("boardDTO", new BoardDTO());
		}
		return "board/boardReadPage";
	}
	
	// 글 수정하는 폼
	@GetMapping("/boardModifyForm")
	public String boardModifyForm(@RequestParam(value = "id", required = false) Integer id
			  , Model model) {
		
		if(id != null) {
			BoardDTO boardDTO = boardService.readBoard(id);
			// db에서 가져온 정보가 없을 경우
			if(boardDTO == null) {
				model.addAttribute("msg", "존재하지 않는 글입니다.");
				model.addAttribute("url", "/board/boardList?page=1&numPerPage=10");
				
				return "board/messageAlert";
			}
			model.addAttribute("boardDTO", boardDTO);
		} else {
			// id가 null일 때 빈 객체 전송
			model.addAttribute("boardDTO", new BoardDTO());
		}
		return "board/boardModifyForm";
	}
		// 글 수정하기
	@PostMapping("/boardModifyOk")
	public String boardModifyOk(@Valid BoardDTO boardDTO
							  , HttpServletRequest request
							  , @AuthenticationPrincipal CustomUserDetails userDetails
							  , Model model) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String writer = request.getParameter("writer");
		String userRole = userDetails.getUser().getRolename();
		String username = userDetails.getUser().getUsername();
		
		// 그 글의 작성자랑 로그인 유저 이름 비교 -> 맞으면 수정 진행
		try {
			// 로그인 유저 또는 관리자가 맞는가
			if(writer.equals(username) || userRole.equals("ADMIN")) {
				boardService.updateBoard(boardDTO);
			} else {
				model.addAttribute("msg", "권한이 없습니다.");
				model.addAttribute("url", "/board/boardList?page=1&numPerPage=10");
				
				return "board/messageAlert";
			}
		} catch (Exception e) {
			log.info("글 수정 중 오류 발생");
		}
		// 다시 상세 글로 이동할 때 id 사용
		return "redirect:/board/boardReadPage?id=" + id;
	}
	
	// 글 삭제
	@PostMapping("/boardDeleteOk")
	public String boardDeleteOk(BoardDTO boardDTO
			  				  , HttpServletRequest request
			  				  , @AuthenticationPrincipal CustomUserDetails userDetails
							  , Model model
							  , PageCriteriaDTO pCriaDTO) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String writer = request.getParameter("writer");
		String userRole = userDetails.getUser().getRolename();
		String username = userDetails.getUser().getUsername();
		
		// 그 글의 작성자랑 로그인 유저 이름 비교 -> 맞으면 삭제 진행
		try {
			// 로그인 유저 또는 관리자가 맞는가
			if(writer.equals(username) || userRole.equals("ADMIN")) {
				int isOk = boardService.deleteBoard(id);
				
				if(isOk != 1) {
					model.addAttribute("msg", "글 삭제를 실패하였습니다.");
					model.addAttribute("url", "/board/boardList?page=1&numPerPage=10");
				} else {
					// 성공 시
					model.addAttribute("msg", "글이 삭제되었습니다.");
					model.addAttribute("url", "/board/boardList?page=1&numPerPage=10");
				}
				
				return "board/messageAlert";
			} else {
				model.addAttribute("msg", "권한이 없습니다.");
				model.addAttribute("url", "/board/boardList?page=1&numPerPage=10");
				
				return "board/messageAlert";
			}
		} catch (DataAccessException e) {
			
		} catch (Exception e) {
			log.info("글 삭제 중 오류 발생");
		}
		return "board/boardList";
	}
	
	/*
	 * 페이징 처리
	 */
/*	@GetMapping("/pageListTest")
	public void pageListTest(PageCriteriaDTO pCriaDTO, Model model) {
			
		model.addAttribute("boardList", boardService.listPageCriteria(pCriaDTO));
		
		PagingDTO pagingDTO = new PagingDTO();
		pagingDTO.setPageCriteriaDTO(pCriaDTO);
		pagingDTO.setTotalData(boardService.countBoardList(pCriaDTO)); // 임의의 값 -> 총 16페이지
		
		model.addAttribute("pagingDTO", pagingDTO);
	}
*/	
	@GetMapping("/boardList")
	public void pageListTest(@Valid FindCreteriaDTO findCreteriaDTO, Model model) {
		
		model.addAttribute("boardList", boardService.listfindCriteria(findCreteriaDTO));
		
		PagingDTO pagingDTO = new PagingDTO();
		pagingDTO.setPageCriteriaDTO(findCreteriaDTO);
		pagingDTO.setTotalData(boardService.findCountData(findCreteriaDTO)); // 임의의 값 -> 총 16페이지
		
		model.addAttribute("pagingDTO", pagingDTO);
	}
}
