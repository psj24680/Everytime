package com.everytime.comment;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.everytime.comment.bo.CommentBO;

@RestController
@RequestMapping("/comment")
public class CommentRestController {

  @Autowired
  private CommentBO commentBO;

  /**
   * 댓글 작성
   * 
   * @param boardId
   * @param postId
   * @param content
   * @param anonymous
   * @param session
   * @return
   */
  @RequestMapping("/create")
  public Map<String, Object> create(
      @RequestParam("boardId") int boardId,
      @RequestParam("postId") int postId,
      @RequestParam("content") String content,
      @RequestParam("anonymous") String anonymous,
      HttpSession session) {
    Map<String, Object> result = new HashMap<>();

    int row = commentBO.addComment(boardId, postId, (String) session.getAttribute("userNickname"),
        content, anonymous);
    if (row == 1) {
      result.put("result", "success");
    } else {
      result.put("result", "댓글 저장을 실패했습니다. 다시 입력해주세요.");
    }

    return result;
  }

  /**
   * 댓글 삭제
   * 
   * @param commentId
   * @param session
   * @return
   */
  @DeleteMapping("/delete")
  public Map<String, Object> delete(
      @RequestParam("commentId") int commentId,
      HttpSession session) {
    Map<String, Object> result = new HashMap<>();

    int row = commentBO.deleteCommentById(commentId);
    if (row == 1) {
      result.put("result", "success");
    } else {
      result.put("result", "댓글 삭제를 실패했습니다. 다시 시도해주세요.");
    }

    return result;
  }

}
