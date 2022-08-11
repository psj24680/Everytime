package com.everytime.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.board.bo.BoardBO;
import com.everytime.board.model.Board;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private BoardBO boardBO;

	/**
	 * 메인 화면
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/main_view")
	public String mainView(Model model) {
		// 게시판 불러오기
		List<Board> boardList = boardBO.getBoardList();
		model.addAttribute("boardList", boardList);

		model.addAttribute("viewName", "main/main_view");

		return "template/layout";
	}

}
