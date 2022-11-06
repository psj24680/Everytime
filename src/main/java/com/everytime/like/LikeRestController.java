package com.everytime.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everytime.like.bo.LikeBO;

@RestController
public class LikeRestController {

  @Autowired
  private LikeBO likeBO;

  /**
   * 좋아요
   * 
   * @param boardId
   * @param postId
   * @param session
   * @return
   */
  @RequestMapping("/like")
  public Map<String, Object> like(
      @RequestParam("boardId") int boardId,
      @RequestParam("postId") int postId,
      HttpSession session) {
    Map<String, Object> result = new HashMap<>();

    likeBO.like(boardId, postId, (int) session.getAttribute("userId"));
    result.put("result", "success");

    return result;
  }

}
