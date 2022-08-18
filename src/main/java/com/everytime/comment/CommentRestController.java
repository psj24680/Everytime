package com.everytime.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everytime.comment.bo.CommentBO;

@RestController
@RequestMapping("/comment")
public class CommentRestController {

	@Autowired
	private CommentBO commentBO;

	@RequestMapping("/create")
	public Map<String, Object> create(
			@RequestParam("boardId") int boardId,
			@RequestParam("postId") int postId,
			@RequestParam("userId") int userId,
			@RequestParam("content") String content,
			@RequestParam("anonymous") String anonymous) {
		Map<String, Object> result = new HashMap<>();

		int row = commentBO.addComment(boardId, postId, userId, content, anonymous);
		if (row == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "댓글 저장을 실패했습니다. 다시 입력해주세요.");
		}

		return result;
	}

}
