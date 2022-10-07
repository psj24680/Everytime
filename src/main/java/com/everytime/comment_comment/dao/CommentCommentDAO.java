package com.everytime.comment_comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.everytime.comment_comment.model.CommentComment;

@Repository
public interface CommentCommentDAO {

	public int insertCommentComment(
			@Param("boardId") int boardId,
			@Param("postId") int postId,
			@Param("commentId") int commentId,
			@Param("nickname") String nickname,
			@Param("content") String content,
			@Param("anonymous") String anonymous);

	public List<CommentComment> selectCommentCommentListByCommentId(int commentId);

	public int selectCommentCommentCountByPostId(int postId);

	public void deleteCommentCommentByPostId(int postId);

}
