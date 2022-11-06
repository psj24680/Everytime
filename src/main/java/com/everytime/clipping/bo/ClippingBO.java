package com.everytime.clipping.bo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.everytime.clipping.dao.ClippingDAO;
import com.everytime.clipping.model.Clipping;

@Service
public class ClippingBO {

  @Autowired
  private ClippingDAO clippingDAO;

  public void clipping(int boardId, int postId, int userId) {
    if (clippingDAO.existClippingByBoardIdAndPostIdAndUserId(boardId, postId, userId) == false) {
      // 스크랩
      clippingDAO.insertClipping(boardId, postId, userId);
    } else {
      // 해제
      clippingDAO.deleteClippingByBoardIdAndPostIdAndUserId(boardId, postId, userId);
    }
  }

  public int getClippingCountByPostId(int postId) {
    return clippingDAO.selectClippingCountByPostId(postId);
  }

  public List<Clipping> getClippingByUserId(int userId) {
    return clippingDAO.selectClippingByUserId(userId);
  }

}
