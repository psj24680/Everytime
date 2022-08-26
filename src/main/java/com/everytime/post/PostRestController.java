package com.everytime.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everytime.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

	@Autowired
	private PostBO postBO;

	@RequestMapping("/create")
	public Map<String, Object> create(
			@RequestParam("boardId") int boardId,
			@RequestParam(value = "subject", required = false) String subject,
			@RequestParam("content") String content,
			@RequestParam("anonymous") String anonymous,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();

		int row = postBO.addPost(boardId, (int) session.getAttribute("userId"), subject, content, anonymous);
		if (row == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "글 저장을 실패했습니다. 다시 입력해주세요.");
		}

		return result;
	}
}
