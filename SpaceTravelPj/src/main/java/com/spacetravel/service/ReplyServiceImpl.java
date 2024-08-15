package com.spacetravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacetravel.dto.PageCriteriaDTO;
import com.spacetravel.dto.ReplyDTO;
import com.spacetravel.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	ReplyMapper replyMapper;
	
	@Override
	public void insertReply(ReplyDTO replyDTO) {
		replyMapper.insertReply(replyDTO);
	}
	
	@Override
	public List<ReplyDTO> replyList(Integer id) {
		return replyMapper.replyList(id);
	}
	
	@Override
	public void modifyReply(ReplyDTO replyDTO) {
		replyMapper.modifyReply(replyDTO);
	}
	
	@Override
	public void deleteReply(Integer replyid) {
		replyMapper.deleteReply(replyid);
	}
	
	@Override
	public List<ReplyDTO> replyListPageButton(Integer id, PageCriteriaDTO pageCriteriaDTO) {
		return replyMapper.replyListPageButton(id, pageCriteriaDTO);
	}
	
	@Override
	public int replyCount(Integer id) {
		return replyMapper.replyCount(id);
	}
	
	@Override
	public void deleteReplyByBoardId(int id) {
		replyMapper.deleteReplyByBoardId(id);
	}

}
