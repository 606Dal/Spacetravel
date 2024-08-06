package com.spacetravel;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.mapper.UserMapper;
import com.spacetravel.service.AdminService;
import com.spacetravel.service.AuthenticationProviderService;
import com.spacetravel.service.UserService;

@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AdminService adminService;
	
	private static Logger log = LoggerFactory.getLogger(UserMapperTest.class);
/*
	@Test
	public void testInsert() {

		UserDTO uDto = new UserDTO();
		
		uDto.setUsername("테스터2");
		uDto.setPassword("12345");
		
		userService.insertUser(uDto);
		
		System.out.println("테스트 유저 추가 성공" + uDto.toString());
	}
	
	
	@Test
	public void testFindUser() {
		log.info(userMapper.findByUsername("관리자").toString());
	}
	

	@Test
	public void testIdCheck() {
		log.info(userMapper.usernameDuplicateCheck("테스터2").toString());
		String result = userService.usernameDuplicateCheck("관리");
		if(result == null) {
			System.out.println("널입니다");
		}
	}
	
	@Test
	public void getCountUser() {
		System.out.println("총 유저 수 : "+adminService.getCountUser());
	}
*/	
	@Test
	public void userList() {
		List<UserDTO> userList = adminService.getUserList();
		System.out.println("List 회원 출력 1 : "+userList.get(0).toString());
	}
	
}
