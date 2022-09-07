package com.everytime.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {

	public void insertLike(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("userId") int userId);

	public boolean existLikeByBoardIdAndPostIdAndUserId(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("userId") int userId);

	public void deleteLikeByBoardIdAndPostIdAndUserId(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("userId") int userId);

	public int selectLikeCountByPostId(int postId);

}
