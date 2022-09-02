package com.everytime.post.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.everytime.post.model.Post;

@Repository
public interface PostDAO {

	public int insertPost(Map<String, Object> postMap);

	public int insertImagePath(Map<String, Object> postMap);

	public String selectImagePathById(int id);

	public List<Post> selectPostListByBoardId(int boardId);

	public Post selectPostById(int id);

}
