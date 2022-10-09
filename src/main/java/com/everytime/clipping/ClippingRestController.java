package com.everytime.clipping;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everytime.clipping.bo.ClippingBO;

@RestController
public class ClippingRestController {

	@Autowired
	private ClippingBO clippingBO;

	@RequestMapping("/clipping")
	public Map<String, Object> clipping(
			@RequestParam("boardId") int boardId,
			@RequestParam("postId") int postId,
			HttpSession session) {
		Map<String, Object> result = new HashMap<>();

		clippingBO.clipping(boardId, postId, (int) session.getAttribute("userId"));
		result.put("result", "success");

		return result;
	}

}
