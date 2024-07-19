package com.spacetravel.service;

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
	// 아이디 중복 체크용
	@Override
	public String usernameDuplicateCheck(String user) {
		return userMapper.usernameDuplicateCheck(user);
	}
	
}
