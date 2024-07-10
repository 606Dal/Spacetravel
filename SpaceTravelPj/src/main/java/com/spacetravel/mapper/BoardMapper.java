package com.spacetravel.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spacetravel.dto.BoardDTO;
/*
 * 게시판
 */
@Mapper
public interface BoardMapper {

	public void insertBoard(BoardDTO boardDTO); // 글 추가
	
}
