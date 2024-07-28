package com.spacetravel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	//@Autowired
	//private UserService userService;
	
	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	//경로 테스트
	@GetMapping("/tpl")
	public String tplFragments() {
		
		return "tpl/tpl_fragments";
	}
	
}
