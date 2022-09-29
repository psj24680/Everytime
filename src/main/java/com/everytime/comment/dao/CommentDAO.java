package com.everytime.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.everytime.comment.model.Comment;

@Repository
public interface CommentDAO {

	public int insertComment(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("nickname") String nickname,
			@Param("content") String content,
			@Param("anonymous") String anonymous);

	public List<Comment> selectCommentListByPostId(int postId);

}
