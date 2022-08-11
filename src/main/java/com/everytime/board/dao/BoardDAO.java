package com.everytime.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.everytime.board.model.Board;

@Repository
public interface BoardDAO {

	public List<Board> selectBoardList();
}
