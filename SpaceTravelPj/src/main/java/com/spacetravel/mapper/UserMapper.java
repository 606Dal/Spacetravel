package com.spacetravel.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spacetravel.dto.UserDTO;
/*
 * 사용자 관련
 */
@Mapper
public interface UserMapper {

	public void insertUser(UserDTO userDTO); // 회원가입
	
	public String usernameDuplicateCheck(String user); // 아이디 중복 체크
	
	/*
	 * 스프링 시큐리티 관련
	 */
	public UserDTO findByUsername(String u); // 사용자 이름으로 정보 조회
	
	public String selectOnePasswordByUsername(String u); // 사용자 이름으로 패스워드 가져오기
	
	public void updatePassword(UserDTO userDTO); // 비밀번호 변경

	public int deleteUser(String u); // 유저 삭제
}
