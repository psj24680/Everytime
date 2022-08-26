package com.everytime.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
			@RequestParam("content") String content,
			@RequestParam("anonymous") String anonymous,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		
		// 로그인 여부 검사
		if ((Integer) session.getAttribute("userId") == null) {
			result.put("result", "로그인 후 이용해주세요.");
			return result;
		}

		int row = commentBO.addComment(boardId, postId, (int) session.getAttribute("userId"), content, anonymous);
		if (row == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "댓글 저장을 실패했습니다. 다시 입력해주세요.");
		}

		return result;
	}

}
