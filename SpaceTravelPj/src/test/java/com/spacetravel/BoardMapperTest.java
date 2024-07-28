package com.spacetravel;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.service.BoardService;

@SpringBootTest
public class BoardMapperTest {
	@Autowired
	private BoardService boardService;
/*	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testInsert() {

		BoardDTO bDto = new BoardDTO();
		
		bDto.setSubject("테스트 제목");
		bDto.setContent("테스트 내용");
		bDto.setWriter("테스터");
		
		boardMapper.insertBoard(bDto);
		
		System.out.println("테스트 글 추가 성공");
		
		
	}

	@Test
	public void testSelectAll() {
		List<BoardDTO> boardList = boardService.getBoardList();
		System.out.println("List 회원 출력 1 : "+boardList.get(0).toString());
	}
	
	@Test
	public void readBoard() {
		
		System.out.println("글 내용 보기 : "+boardService.readBoard(3));
	}
*/	
	@Test
	public void updateBoard() {
		
		System.out.println("글 내용 보기 : "+boardService.readBoard(7));
		BoardDTO bDto = new BoardDTO();
		bDto.setId(7);
		bDto.setSubject("수정 매퍼 테스트");
		bDto.setContent("수정 매퍼 테스트11");
		
		boardService.updateBoard(bDto);
		
		System.out.println("글 수정 내용 보기 : "+boardService.readBoard(7));
	}
	
}
