package com.everytime.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.comment.dao.CommentDAO;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;

	public int addComment(int boardId, int postId, int userId, String content, String anonymous) {
		return commentDAO.insertComment(boardId, postId, userId, content, anonymous);
	}

}
