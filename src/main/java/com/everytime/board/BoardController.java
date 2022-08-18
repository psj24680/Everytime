package com.everytime.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.board.bo.BoardBO;
import com.everytime.post.bo.PostBO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardBO boardBO;

	@Autowired
	private PostBO postBO;

	/**
	 * 게시판 화면
	 * 
	 * @param boardId
	 * @param model
	 * @return
	 */
	@RequestMapping("/{boardId}")
	public String post_list_view(
			@PathVariable int boardId,
			Model model) {
		// boardId로 게시판 이름 가져오기
		model.addAttribute("board", boardBO.getBoardByBoardId(boardId));

		// boardId로 게시판에 저장된 게시글 목록 불러오기
		model.addAttribute("postList", postBO.getPostListByBoardId(boardId));

		// JSP 경로
		model.addAttribute("viewName", "board/post_list_view");

		return "template/layout";
	}

}
