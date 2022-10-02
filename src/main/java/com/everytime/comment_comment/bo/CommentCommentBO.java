package com.everytime.comment_comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.comment_comment.dao.CommentCommentDAO;
import com.everytime.comment_comment.model.CommentComment;

@Service
public class CommentCommentBO {

	@Autowired
	private CommentCommentDAO commentCommentDAO;

	public int addCommentComment(int boardId, int postId, int commentId, String nickname, String content, String anonymous) {
		return commentCommentDAO.insertCommentComment(boardId, postId, commentId, nickname, content, anonymous);
	}

	/** 
	 * 댓글 ID로 대댓글 목록 불러오기
	 * 
	 * @param commentId
	 * @return
	 */
	public List<CommentComment> getCommentCommentListByCommentId(int commentId) {
		return commentCommentDAO.selectCommentCommentListByCommentId(commentId);
	}

	public int getCommentCommentCountByPostId(int postId) {
		return commentCommentDAO.selectCommentCommentCountByPostId(postId);
	}

}
