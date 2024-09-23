package com.spacetravel.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void insertUser(UserDTO userDTO) {
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		// 인코딩된 패스워드를 저장
		userDTO.setPassword(encodedPassword);

		userMapper.insertUser(userDTO);
	}

	// 아이디 중복 체크
	@Override
	public String usernameDuplicateCheck(String user) {
		return userMapper.usernameDuplicateCheck(user);
	}

	// 현재 로그인한 유저의 비밀번호를 가져와서 입력한 비밀번호와 비교
	@Override
	public boolean isMatchesPassword(String u, String currentPassword) {
		String existingPassword = userMapper.selectOnePasswordByUsername(u);

		if (!passwordEncoder.matches(currentPassword, existingPassword)) {
			return false;
		}
		return true;
	}

	// 비밀번호 변경
	@Override
	public void updatePassword(UserDTO userDTO) {
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		// 인코딩된 패스워드를 새로 저장
		userDTO.setPassword(encodedPassword);
		userMapper.updatePassword(userDTO);
	}

	// 회원 탈퇴
	@Override
	public int deleteAccount(String u) {
		return userMapper.deleteUser(u);
	}

	// 회원 가입일
	@Override
	public Date findUserDate(String u) {
		return userMapper.findUserDate(u);
	}

}
