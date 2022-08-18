package com.everytime.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.post.bo.PostBO;

@Controller
@RequestMapping("/board")
public class PostController {

	@Autowired
	private PostBO postBO;

	/**
	 * 글 상세 화면
	 * 
	 * @param boardId
	 * @param postId
	 * @param model
	 * @return
	 */
	@RequestMapping("/{boardId}/post/post_detail_view/{postId}")
	public String postDetailView(
			@PathVariable("boardId") int boardId,
			@PathVariable("postId") int postId,
			Model model) {
		// postId로 게시글 내용 가져오기
		model.addAttribute("post", postBO.getPostById(postId));

		// JSP 경로
		model.addAttribute("viewName", "post/post_detail_view");

		return "template/layout";
	}

}
