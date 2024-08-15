package com.spacetravel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.dto.FindCriteriaDTO;
import com.spacetravel.dto.PageCriteriaDTO;
/*
 * 게시판
 */
@Mapper
public interface BoardMapper {

	public void insertBoard(BoardDTO boardDTO); // 글 추가

	public List<BoardDTO> selectBoardAll(); // 글 목록 가져오기

	public BoardDTO readBoard(Integer id); // 글 내용 보기

	public void updateBoard(BoardDTO boardDTO); // 글 수정

	public int deleteBoard(int id); // 글 삭제
	
	public void updateHit(int id); // 조회수 올리기
	
	/*
	 * 페이징 처리를 위한 메서드
	 */
	public List<BoardDTO> listPageCriteria(PageCriteriaDTO pageCriteriaDTO); // 페이지 기준 글 목록 가져오기

	public int countBoardList(PageCriteriaDTO pageCriteriaDTO); // 글의 총 개수

	public List<BoardDTO> listfindCriteria(FindCriteriaDTO findCreteriaDTO); // 검색 기준 글 목록

	public int findCountData(FindCriteriaDTO findCreteriaDTO); // 검색된 글 개수

}
