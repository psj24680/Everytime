package com.everytime.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	/**
	 * 로그인 화면
	 * 
	 * @return
	 */
	@RequestMapping("/sign_in_view")
	public String signInView() {
		return "user/sign_in_view";
	}

	/**
	 * 회원가입 화면
	 * 
	 * @return
	 */
	@RequestMapping("/sign_up_view")
	public String signUpView() {
		return "user/sign_up_view";
	}

	/**
	 * 로그아웃
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");

		return "user/sign_in_view";
	}

}
