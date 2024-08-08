package com.spacetravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacetravel.dto.BoardDTO;
import com.spacetravel.dto.FindCriteriaDTO;
import com.spacetravel.dto.PageCriteriaDTO;
import com.spacetravel.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public void insertBoard(BoardDTO boardDTO) {

		boardMapper.insertBoard(boardDTO);
	}
	
	@Override
	public List<BoardDTO> getBoardList() {
		return boardMapper.selectBoardAll();
	}
	
	@Override
	public BoardDTO readBoard(Integer id) {
		return boardMapper.readBoard(id);
	}
	
	@Override
	public void updateBoard(BoardDTO boardDTO) {
		boardMapper.updateBoard(boardDTO);
	}
	
	@Override
	public int deleteBoard(int id) {
		return boardMapper.deleteBoard(id);
	}
	
	@Override
	public void updateHit(int id) {
		boardMapper.updateHit(id);
	}
	
	/*
	 * 페이징 처리
	 */
/*	@Override
	public List<BoardDTO> listPageCriteria(PageCriteriaDTO pageCriteriaDTO) {
		return boardMapper.listPageCriteria(pageCriteriaDTO);
	}
*/	
	@Override
	public int countBoardList(PageCriteriaDTO pageCriteriaDTO) {
		return boardMapper.countBoardList(pageCriteriaDTO);
	}
	
	@Override
	public List<BoardDTO> listfindCriteria(FindCriteriaDTO findCreteriaDTO) {
		return boardMapper.listfindCriteria(findCreteriaDTO);
	}
	
	@Override
	public int findCountData(FindCriteriaDTO findCreteriaDTO) {
		return boardMapper.findCountData(findCreteriaDTO);
	}

}
