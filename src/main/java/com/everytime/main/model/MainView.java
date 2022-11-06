package com.everytime.main.model;

import java.util.List;

import com.everytime.board.model.Board;
import com.everytime.post.model.Post;

public class MainView {
  private Board board;
  private List<Post> recentPostList;

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public List<Post> getRecentPostList() {
    return recentPostList;
  }

  public void setRecentPostList(List<Post> recentPostList) {
    this.recentPostList = recentPostList;
  }
}
