package com.everytime.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.everytime.post.model.Post;

@Repository
public interface PostDAO {

  public int insertPost(Map<String, Object> postMap);

  public int insertImagePath(Map<String, Object> imagePathMap);

  public List<String> selectImagePathListById(int id);

  public List<Post> selectPostListByBoardId(int boardId);

  public List<Post> selectRecentPostListByBoardId(int boardId);

  public Post selectPostById(int id);

  public List<Post> selectPostListByNickname(String nickname);

  public void deleteImagePathById(int id);

  public int deletePostByIdAndNickname(@Param("id") int id, @Param("nickname") String nickname);

}
