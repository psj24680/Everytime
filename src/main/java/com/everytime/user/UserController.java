package com.everytime.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/sign_in_view")
	public String signInView() {
		return "user/sign_in_view";
	}
	
	@RequestMapping("/test")
	public String test() {
		return "test/index";
	}
}
