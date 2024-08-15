package com.spacetravel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spacetravel.dto.PageCriteriaDTO;
import com.spacetravel.dto.ReplyDTO;
/*
 * 댓글 기능
 */
@Mapper
public interface ReplyMapper {

	public void insertReply(ReplyDTO replyDTO); // 댓글 등록

	public List<ReplyDTO> replyList(Integer id); // 댓글 목록

	public void modifyReply(ReplyDTO replyDTO); // 댓글 수정

	public void deleteReply(Integer replyid); // 댓글 삭제
	// 댓글 페이지 버튼
	public List<ReplyDTO> replyListPageButton(Integer id, PageCriteriaDTO pageCriteriaDTO);

	public int replyCount(Integer id); // 댓글 총개수

	public void deleteReplyByBoardId(int id); // 게시판 글 삭제 전 댓글 삭제

}
