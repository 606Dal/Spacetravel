package com.spacetravel;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.mapper.BoardMapper;

@SpringBootTest
public class BoardMapperTest {
	
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

}
