package com.spacetravel;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacetravel.dto.PageCriteriaDTO;
import com.spacetravel.dto.ReplyDTO;
import com.spacetravel.service.ReplyService;

@SpringBootTest
public class ReplyMapperTest {

	@Autowired
	private ReplyService replyService;
	
	private static final Logger log = LoggerFactory.getLogger(BoardMapperTest.class);
/*	
	@Test
	public void writeTest() {
		ReplyDTO rDto = new ReplyDTO();
		rDto.setId(251);
		rDto.setReplyContent("댓글 테스트");
		rDto.setReplier("테스터2");
		
		replyService.insertReply(rDto);
		log.info("rDto"+rDto);
	}

	@Test
	public void relistTest() throws Exception {
		log.info(replyService.replyList(251).toString());
	}

	@Test
	public void updateRelistTest() {
		
		System.out.println("댓글 내용 보기 : "+replyService.replyList(251).toString());
		ReplyDTO rDto = new ReplyDTO();
		rDto.setReplyid(1);
		rDto.setReplyContent("댓글 수정 매퍼 테스트2");
		
		replyService.modifyReply(rDto);
		
		System.out.println("댓글 수정 내용 보기 : "+replyService.replyList(251).toString());
	}

	@Test
	public void updateRelistTest() {
		
		System.out.println("댓글 내용 보기 : "+replyService.replyList(251).toString());
		
		replyService.deleteReply(16);
		
		System.out.println("댓글 수정 내용 보기 : "+replyService.replyList(251).toString());
	}

	@Test
	public void listPageCriteriaTest() {
		
		PageCriteriaDTO pageDTO = new PageCriteriaDTO();
		pageDTO.setPage(1);
		pageDTO.setNumPerPage(5); // 페이지 당 글 개수
		
		List<ReplyDTO> list = replyService.replyListPageButton(251, pageDTO);
		
		for (ReplyDTO rDTO : list) {
			log.info(rDTO.getReplyid() + " : " + rDTO.getReplyContent());
		}
	}
	*/
	@Test
	public void deleteReplyByBoardId() {
		
		replyService.deleteReplyByBoardId(256);
	}
	
}
