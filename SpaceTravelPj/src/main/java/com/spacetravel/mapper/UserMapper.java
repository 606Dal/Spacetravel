package com.spacetravel.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spacetravel.dto.UserDTO;

@Mapper
public interface UserMapper {

	public void insertUser(UserDTO userDTO); // 회원가입
	
	public String usernameDuplicateCheck(String user); // 아이디 중복 체크
	
	/*
	 * 스프링 시큐리티 관련
	 */
	public UserDTO findByUsername(String u); // 사용자 이름으로 정보 조회
	
	public String selectUserRolesByUsername(String u); // 사용자 이름으로 권한 조회
}
