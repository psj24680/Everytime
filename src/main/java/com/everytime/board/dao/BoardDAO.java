package com.everytime.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.everytime.board.model.Board;

@Repository
public interface BoardDAO {

	// 게시판 목록 불러오기
	public List<Board> selectBoardList();
	
	// boardId로 게시판 불러오기
	public Board selectBoardByBoardId(int boardId);
}
