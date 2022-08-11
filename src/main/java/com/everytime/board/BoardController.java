package com.everytime.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	@RequestMapping("/{boardId}/post_list_view")
	public String post_list_view(
			@PathVariable int boardId) {
		return "board/board";
	}
}
