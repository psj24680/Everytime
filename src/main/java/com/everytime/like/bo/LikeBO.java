package com.everytime.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.like.dao.LikeDAO;

@Service
public class LikeBO {

  @Autowired
  private LikeDAO likeDAO;

  public void like(int boardId, int postId, int userId) {
    if (likeDAO.existLikeByBoardIdAndPostIdAndUserId(boardId, postId, userId) == false) {
      // 좋아요
      likeDAO.insertLike(boardId, postId, userId);
    } else {
      // 해제
      likeDAO.deleteLikeByBoardIdAndPostIdAndUserId(boardId, postId, userId);
    }
  }

  public int getLikeCountByPostId(int postId) {
    return likeDAO.selectLikeCountByPostId(postId);
  }

  public void deleteLikeByPostId(int postId) {
    likeDAO.deleteLikeByPostId(postId);
  }

}
