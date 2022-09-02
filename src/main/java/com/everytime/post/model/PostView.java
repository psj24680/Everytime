package com.everytime.post.model;

import java.util.List;

import com.everytime.comment.model.CommentView;
import com.everytime.user.model.User;

public class PostView {
	private Post post;
	private String imagePath;
	private User user;
	private List<CommentView> commentViewList;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
