package com.spacetravel.service;

import org.springframework.stereotype.Service;

import com.spacetravel.dto.BoardDTO;

@Service
public interface BoardService {
	
	public void insertBoard(BoardDTO boardDTO);

}
