package com.everytime.comment.model;

import java.util.List;

import com.everytime.comment_comment.model.CommentComment;
import com.everytime.user.model.User;

public class CommentView {
	private Comment comment;
	private User user;
	private List<CommentComment> comment_comment;

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentComment> getComment_comment() {
		return comment_comment;
	}

	public void setComment_comment(List<CommentComment> comment_comment) {
		this.comment_comment = comment_comment;
	}
}
