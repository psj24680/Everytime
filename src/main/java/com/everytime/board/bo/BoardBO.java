package com.everytime.board.bo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.everytime.board.dao.BoardDAO;
import com.everytime.board.model.Board;

@Service
public class BoardBO {

  @Autowired
  private BoardDAO boardDAO;

  public List<Board> getBoardList() {
    return boardDAO.selectBoardList();
  }

  public Board getBoardByBoardId(int boardId) {
    return boardDAO.selectBoardByBoardId(boardId);
  }

}
