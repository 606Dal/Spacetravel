package com.spacetravel;


import java.util.List;

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
*/
	@Test
	public void testSelectAll() {
		List<BoardDTO> boardList = boardService.getBoardList();
		System.out.println("List 회원 출력 1 : "+boardList.get(0).toString());
	}
	
}
