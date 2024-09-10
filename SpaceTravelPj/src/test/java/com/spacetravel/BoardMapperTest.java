package com.spacetravel;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.dto.FindCriteriaDTO;
import com.spacetravel.service.BoardService;

@SpringBootTest
public class BoardMapperTest {
	@Autowired
	private BoardService boardService;
	
	private static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);
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
		System.out.println("List 출력 1 : "+boardList.get(0).toString());
	}
	
	@Test
	public void readBoard() {
		
		System.out.println("글 내용 보기 : "+boardService.readBoard(3));
	}
*
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

	@Test
	public void listPageCriteriaTest() {
		
		PageCriteriaDTO pageDTO = new PageCriteriaDTO();
		pageDTO.setPage(3);
		pageDTO.setNumPerPage(15); // 페이지 당 글 개수
		
		List<BoardDTO> list = boardService.listPageCriteria(pageDTO);
		
		for (BoardDTO bDTO : list) {
			log.info(bDTO.getId() + " : " + bDTO.getSubject());
		}
	}
	
	@Test
	public void testFind() throws Exception{
		FindCriteriaDTO findCreteriaDTO = new FindCriteriaDTO();
		findCreteriaDTO.setPage(1); //첫 페이지 1
		findCreteriaDTO.setFindType("S"); // 찾는 유형 : 제목
		findCreteriaDTO.setKeyword("테스트"); // 검색어
		
		log.info("************ 글 목록 테스트 출력 ************");
		
		List<BoardDTO> list = boardService.listfindCriteria(findCreteriaDTO);
		//실제 검색은 아직 안 되지만 목록을 10개 출력하면 제대로 동작 중인 것
		for(BoardDTO bDTO : list) {
			log.info(bDTO.getId() + ": " + bDTO.getSubject());
		}
			
		log.info("************ 테스트 Data 개수 출력 ************");
		log.info("CountData: " + boardService.findCountData(findCreteriaDTO));
	}
	
	@Test
	public void uriTest2() throws Exception {
		FindCriteriaDTO findCreteriaDTO = new FindCriteriaDTO();
		findCreteriaDTO.setPage(1); //첫 페이지 1
		findCreteriaDTO.setFindType("S"); // 찾는 유형 : 제목
		findCreteriaDTO.setKeyword("테스트"); // 검색어
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("page", findCreteriaDTO.getPage())
				.queryParam("numPerPage", 20)
				.queryParam("findType", findCreteriaDTO.getFindType())
				.queryParam("keyword", findCreteriaDTO.getKeyword())
				.build();
		
		log.info("/bbs/read?bid=100&numPerPage=20");
		log.info("uriComponents : " + uriComponents.toString());
	}
	*/
}
