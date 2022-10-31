package com.everytime.post.model;

import java.util.List;

import com.everytime.comment.model.CommentView;

public class PostView {
	private Post post;
	private List<String> imagePath;
	private List<CommentView> commentViewList;
	private int likeCount;
	private int clippingCount;
	private int commentCount;

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<String> getImagePath() {
		return imagePath;
	}

	public void setImagePath(List<String> imagePath) {
		this.imagePath = imagePath;
	}

	public List<CommentView> getCommentViewList() {
		return commentViewList;
	}

	public void setCommentViewList(List<CommentView> commentViewList) {
		this.commentViewList = commentViewList;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getClippingCount() {
		return clippingCount;
	}

	public void setClippingCount(int clippingCount) {
		this.clippingCount = clippingCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
}
