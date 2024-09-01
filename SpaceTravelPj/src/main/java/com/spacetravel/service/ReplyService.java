package com.spacetravel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spacetravel.dto.PageCriteriaDTO;
import com.spacetravel.dto.ReplyDTO;

@Service
public interface ReplyService {

	public void insertReply(ReplyDTO replyDTO);

	public List<ReplyDTO> replyList(Integer id);

	public void modifyReply(ReplyDTO replyDTO);

	public void deleteReply(Integer replyid);

	public List<ReplyDTO> replyListPageButton(Integer id, PageCriteriaDTO pageCriteriaDTO);

	public int replyCount(Integer id);

	public void deleteReplyByBoardId(int id);
	
}
