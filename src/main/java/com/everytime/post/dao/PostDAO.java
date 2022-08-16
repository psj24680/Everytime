package com.everytime.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.everytime.post.model.Post;

@Repository
public interface PostDAO {

	public int insertPost(
			@Param("boardId") int boardId,
			@Param("userId") int userId,
			@Param("subject") String subject,
			@Param("content") String content,
			@Param("anonymous") String anonymous);

	public List<Post> selectPostListByBoardId(int boardId);
	
	public Post selectPostById(int id);
}
