package com.everytime.board.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.board.dao.BoardDAO;
import com.everytime.board.model.Board;

@Service
public class BoardBO {

	@Autowired
	BoardDAO boardDAO;

	// 게시판 목록 불러오기
	public List<Board> getBoardList() {
		return boardDAO.selectBoardList();
	}

	// boardId로 게시판 불러오기
	public Board getBoardByBoardId(int boardId) {
		return boardDAO.selectBoardByBoardId(boardId);
	}
}
