package com.everytime.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.post.dao.PostDAO;
import com.everytime.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;

	public int addPost(int boardId, int userId, String subject, String content, String anonymous) {
		return postDAO.insertPost(boardId, userId, subject, content, anonymous);
	}

	public List<Post> getPostListByBoardId(int boardId) {
		return postDAO.selectPostListByBoardId(boardId);
	}

	public Post getPostById(int id) {
		return postDAO.selectPostById(id);
	}
}
