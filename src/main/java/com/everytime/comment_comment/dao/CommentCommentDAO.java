package com.everytime.comment_comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentCommentDAO {

	public int insertCommentComment(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("commentId") int commentId,
			@Param("userId") int userId,
			@Param("content") String content,
			@Param("anonymous") String anonymous);

}
