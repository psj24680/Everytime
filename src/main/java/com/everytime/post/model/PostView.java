package com.everytime.post.model;

import java.util.List;

import com.everytime.comment.model.CommentView;
import com.everytime.user.model.User;

public class PostView {
	private Post post;
	private User user;
	private List<CommentView> commentViewList;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CommentView> getCommentViewList() {
		return commentViewList;
	}

	public void setCommentViewList(List<CommentView> commentViewList) {
		this.commentViewList = commentViewList;
	}
}
