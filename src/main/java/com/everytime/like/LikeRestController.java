package com.everytime.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everytime.like.bo.LikeBO;

@RestController
public class LikeRestController {

	@Autowired
	private LikeBO likeBO;

	@RequestMapping("/like")
	public Map<String, Object> like(
			@RequestParam("boardId") int boardId,
			@RequestParam("postId") int postId,
			HttpSession session) {
		int userId = (int) session.getAttribute("userId");

		likeBO.like(boardId, postId, userId);

		// 결과 return
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");

		return result;
	}

}
