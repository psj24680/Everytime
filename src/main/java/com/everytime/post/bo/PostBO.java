package com.everytime.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.post.dao.PostDAO;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;

	public int addPost(int boardId, int userId, String subject, String content, String anonymous) {
		return postDAO.insertPost(boardId, userId, subject, content, anonymous);
	}
}
