package com.spacetravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacetravel.dto.BoardDTO;
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

}
