package com.everytime.comment_comment.model;

import java.util.Date;

public class CommentComment {
  private int id;
  private int boardId;
  private int postId;
  private int commentId;
  private String nickname;
  private String content;
  private String anonymous;
  private Date createdAt;
  private Date updatedAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public int getCommentId() {
    return commentId;
  }

  public void setCommentId(int commentId) {
    this.commentId = commentId;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAnonymous() {
    return anonymous;
  }

  public void setAnonymous(String anonymous) {
    this.anonymous = anonymous;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
