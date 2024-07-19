package com.spacetravel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spacetravel.dto.BoardDTO;

@Service
public interface BoardService {
	
	public void insertBoard(BoardDTO boardDTO);

	public List<BoardDTO> getBoardList();

}
