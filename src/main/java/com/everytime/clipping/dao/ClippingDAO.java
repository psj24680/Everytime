package com.everytime.clipping.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.everytime.clipping.model.Clipping;

@Repository
public interface ClippingDAO {

  public void insertClipping(@Param("boardId") int boardId, @Param("postId") int postId,
      @Param("userId") int userId);

  public boolean existClippingByBoardIdAndPostIdAndUserId(@Param("boardId") int boardId,
      @Param("postId") int postId, @Param("userId") int userId);

  public void deleteClippingByBoardIdAndPostIdAndUserId(@Param("boardId") int boardId,
      @Param("postId") int postId, @Param("userId") int userId);

  public int selectClippingCountByPostId(int postId);

  public List<Clipping> selectClippingByUserId(int userId);

}
