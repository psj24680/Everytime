package com.everytime.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.user.bo.UserBO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserBO userBO;

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

	/**
	 * 내 정보 화면
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/my_page_view")
	public String myPageView(HttpSession session, Model model) {
		model.addAttribute("user", userBO.getUserById((int) session.getAttribute("userId")));
		model.addAttribute("viewName", "user/my_page_view");

		return "template/layout";
	}

}
