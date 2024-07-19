package com.spacetravel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spacetravel.dto.UserDTO;
import com.spacetravel.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/login")
	public String loginPage() {
		return "user/loginPage";
	}
	
	@GetMapping("/loginOk")
	public String loginOk(Model model) {
		
		model.addAttribute("msg", "로그인에 성공하였습니다.");
		model.addAttribute("url", "/board/main");
		
		return "board/MessageAlert";
	}
	
	// 회원 가입
	@GetMapping("/singUp")
	public String singUpForm() {
		
		return "user/singUpForm";
	}
	  // 유저 등록
	@PostMapping("/singUpOk")
	public String singUpOk(@Valid UserDTO userDTO,
							@RequestParam(value = "username") String username,
							Model model) {
		System.out.println("username : " + username);
		try {
			String result = userService.usernameDuplicateCheck(username);
			// DB에서 검색된 아이디가 없으면
			if(result == null) {
				userService.insertUser(userDTO);
				
				model.addAttribute("msg", "회원 가입에 성공하였습니다.");
				model.addAttribute("url", "user/loginPage");
				
				return "board/MessageAlert";
			} else {
				model.addAttribute("msg", "중복된 아이디 입니다.");
				model.addAttribute("reUsername", username);
			}
		} catch (Exception e) {
			log.info("회원가입 중 오류 발생");
		}
		
		return "user/singUpForm";
	}
	
}
