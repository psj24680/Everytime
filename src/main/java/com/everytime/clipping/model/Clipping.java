package com.everytime.clipping.model;

import java.util.Date;

public class Clipping {
  private int boardId;
  private int postId;
  private int userId;
  private Date createdAt;

  public int getBoardId() {
    return boardId;
  }

  public void setBoardId(int boardId) {
    this.boardId = boardId;
  }

  public int getPostId() {
    return postId;
  }

  public void setPostId(int postId) {
    this.postId = postId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
}
