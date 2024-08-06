package com.spacetravel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spacetravel.dto.UserDTO;
/*
 * 관리자 관련
 */
@Mapper
public interface AdminMapper {

	public int getCountUser(); // 총 유저

	public List<UserDTO> getUserList(); // 유저 목록 가져오기

}
