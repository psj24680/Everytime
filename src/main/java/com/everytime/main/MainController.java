package com.everytime.main;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.board.bo.BoardBO;
import com.everytime.board.model.Board;
import com.everytime.user.bo.UserBO;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private UserBO userBO;

	@Autowired
	private BoardBO boardBO;

	/**
	 * 메인 화면
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/main_view")
	public String mainView(Model model, HttpSession session) {
		// 유저 학교 불러오기
		int userId = (int) session.getAttribute("userId");
		model.addAttribute("user", userBO.getUserById(userId));

		// 게시판 불러오기
		List<Board> boardList = boardBO.getBoardList();
		model.addAttribute("boardList", boardList);

		model.addAttribute("viewName", "main/main_view");

		return "template/layout";
	}

}
