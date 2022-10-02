package com.everytime.post;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.post.bo.PostBO;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostBO postBO;

	/**
	 * 내가 쓴 글 화면
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/my_post_view")
	public String myPostView(
			HttpSession session,
			Model model) {
		// 로그인 시 세션에 저장된 nickname으로 게시글 불러오기
		model.addAttribute("myPostList", postBO.getPostListByNickname((String) session.getAttribute("userNickname")));
		model.addAttribute("viewName", "post/my_post_view");

		return "template/layout";
	}
}
