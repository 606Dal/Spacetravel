package com.spacetravel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.mapper.AdminMapper;
import com.spacetravel.mapper.UserMapper;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int getCountUser() {
		return adminMapper.getCountUser();
	}
	
	@Override
	public List<UserDTO> getUserList() {
		return adminMapper.getUserList();
	}
	
	@Override
	public int deleteUser(String username) {
		return userMapper.deleteUser(username);
	}
	
}
