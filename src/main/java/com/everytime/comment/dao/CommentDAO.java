package com.everytime.comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO {

	public int insertComment(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("anonymous") String anonymous);

}
