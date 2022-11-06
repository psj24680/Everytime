package com.everytime.main;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.board.bo.BoardBO;
import com.everytime.board.model.Board;
import com.everytime.main.model.MainView;
import com.everytime.post.bo.PostBO;
import com.everytime.user.bo.UserBO;

@Controller
@RequestMapping("/main")
public class MainController {

  @Autowired
  private UserBO userBO;

  @Autowired
  private BoardBO boardBO;

  @Autowired
  private PostBO postBO;

  /**
   * 메인 화면
   * 
   * @param model
   * @param session
   * @return
   */
  @RequestMapping("/main_view")
  public String mainView(
      Model model,
      HttpSession session) {
    // 유저 정보 불러오기
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId != null) {
      model.addAttribute("user", userBO.getUserById(userId));
    }

    // 최근 게시글 불러오기 - TODO: BO에서 처리하기
    Map<String, Object> boardMap = new LinkedHashMap<>();
    List<Board> boardList = boardBO.getBoardList();
    for (Board board : boardList) {
      MainView mainView = new MainView();
      mainView.setBoard(board);
      mainView.setRecentPostList(postBO.getRecentPostListByBoardId(board.getId()));
      boardMap.put(board.getName(), mainView);
    }

    model.addAttribute("boardMap", boardMap);
    model.addAttribute("viewName", "main/main_view");

    return "template/layout";
  }

}
