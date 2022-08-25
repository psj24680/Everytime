package com.everytime.comment_comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.comment_comment.dao.CommentCommentDAO;

@Service
public class CommentCommentBO {

	@Autowired
	private CommentCommentDAO commentCommentDAO;

	public int addCommentComment(int boardId, int postId, int commentId, int userId, String content, String anonymous) {
		return commentCommentDAO.insertCommentComment(boardId, postId, commentId, userId, content, anonymous);
	}

}
