package com.spacetravel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spacetravel.dto.ReplyDTO;
import com.spacetravel.service.CustomUserDetails;
import com.spacetravel.service.ReplyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	// 댓글 추가
	@PostMapping("/writeReply")
	public ResponseEntity<String> writeReply(
										  @Valid @RequestBody ReplyDTO replyDTO
										, Authentication authentication){
		
		ResponseEntity<String> resEntity = null;
		
		try {
			replyDTO.setReplier(authentication.getName());
			replyService.insertReply(replyDTO);
				
			resEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {
			log.info("댓글 추가 중 오류 발생");
		}

		return resEntity;
	}

/*
 * // 댓글 리스트 가져오기
 * 시큐리티 처리 때문에 boardreadPage에서 목록 출력함
 */
  
    // 댓글 수정하기
    @PutMapping("/{replyid}")
    public ResponseEntity<String> modifyReply(
    										  @PathVariable("replyid") Integer replyid
    										, @RequestBody ReplyDTO replyDTO
    										, @AuthenticationPrincipal CustomUserDetails userDetails){
		
		ResponseEntity<String> resEntity = null;
		String replier = replyDTO.getReplier();
		String userRole = userDetails.getUser().getRolename();
		String username = userDetails.getUser().getUsername();
		
		try {
			  if(replier.equals(username) || userRole.equals("ADMIN")) {
				  replyDTO.setReplyid(replyid);
				  replyService.modifyReply(replyDTO);
				  resEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
			  }
		} catch (Exception e) {
			log.info("댓글 수정 중 오류 발생");
		}
		return resEntity;
	}
    
    // 댓글 삭제하기
    @DeleteMapping("/{replyid}")
    public ResponseEntity<String> deleteReply(
    										  @PathVariable("replyid") Integer replyid
    										, @RequestBody ReplyDTO replyDTO
    										, @AuthenticationPrincipal CustomUserDetails userDetails){
    	
    	ResponseEntity<String> resEntity = null;
    	String replier = replyDTO.getReplier();
    	String userRole = userDetails.getUser().getRolename();
    	String username = userDetails.getUser().getUsername();
    	
    	try {
    		if(replier.equals(username) || userRole.equals("ADMIN")) {
    			replyDTO.setReplyid(replyid);
    			replyService.deleteReply(replyid);
    			resEntity = new ResponseEntity<String>("Success", HttpStatus.OK);
    		}
    	} catch (Exception e) {
    		log.info("댓글 삭제 중 오류 발생");
    	}
    	return resEntity;
    }
    
}
