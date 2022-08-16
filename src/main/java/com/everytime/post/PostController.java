package com.everytime.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.post.bo.PostBO;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostBO postBO;

	@RequestMapping("/post_detail_view/{postId}")
	public String postDetailView(
			@PathVariable("postId") int postId,
			Model model) {
		model.addAttribute("post", postBO.getPostById(postId));
		model.addAttribute("viewName", "post/post_detail_view");

		return "template/layout";
	}
}
