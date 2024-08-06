package com.spacetravel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.dto.FindCreteriaDTO;
import com.spacetravel.dto.PageCriteriaDTO;

@Service
public interface BoardService {
	
	public void insertBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

	public BoardDTO readBoard(Integer id);

	public void updateBoard(BoardDTO boardDTO);

	public int deleteBoard(int id);

	/*
	 * 페이징 처리를 위한
	 */
	public List<BoardDTO> listPageCriteria(PageCriteriaDTO pageCriteriaDTO);

	public int countBoardList(PageCriteriaDTO pageCriteriaDTO);

	public List<BoardDTO> listfindCriteria(FindCreteriaDTO findCreteriaDTO);

	public int findCountData(FindCreteriaDTO findCreteriaDTO);
}
