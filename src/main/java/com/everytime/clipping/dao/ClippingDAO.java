package com.everytime.clipping.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClippingDAO {

	public void insertClipping(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("userId") int userId);

	public boolean existClippingByBoardIdAndPostIdAndUserId(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("userId") int userId);

	public void deleteClippingByBoardIdAndPostIdAndUserId(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("userId") int userId);

	public int selectClippingCountByPostId(int postId);

}
