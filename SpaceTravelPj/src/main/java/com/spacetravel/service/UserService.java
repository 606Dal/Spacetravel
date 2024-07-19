package com.spacetravel.service;

import org.springframework.stereotype.Service;

import com.spacetravel.dto.UserDTO;

@Service
public interface UserService {

	public void insertUser(UserDTO userDTO);
	
	public String usernameDuplicateCheck(String u);
	
}
