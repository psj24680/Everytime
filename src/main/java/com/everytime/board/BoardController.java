package com.everytime.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.board.bo.BoardBO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardBO boardBO;
	
	@RequestMapping("/{boardId}/post_list_view")
	public String post_list_view(
			@PathVariable int boardId,
			Model model) {
		model.addAttribute("board", boardBO.getBoardByBoardId(boardId));
		model.addAttribute("viewName", "board/post_list_view");
		
		return "template/layout";
	}
}
