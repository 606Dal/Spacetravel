package com.spacetravel.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import com.spacetravel.dto.UserDTO;

@Mapper
public interface UserMapper {

	void insertUser(UserDTO userDTO); // 회원가입
	
	String usernameDuplicateCheck(String username); // 아이디 중복 체크
	
	Date findUserDate(String username); // 회원 가입일
	
	UserDTO findByUsername(String username); // 사용자 이름으로 정보 조회
	
	String selectOnePasswordByUsername(String username); // 사용자 이름으로 패스워드 가져오기
	
	void updatePassword(UserDTO userDTO); // 비밀번호 변경

	int deleteUser(String username); // 유저 삭제(상태 변경)
	
	int updateStatus(UserDTO userDTO);

}
