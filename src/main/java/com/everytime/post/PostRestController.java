package com.everytime.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.everytime.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {

  @Autowired
  private PostBO postBO;

  /**
   * 글 저장
   * 
   * @param boardId
   * @param subject
   * @param content
   * @param anonymous
   * @param files
   * @param session
   * @return
   */
  @RequestMapping("/create")
  public Map<String, Object> create(
      @RequestParam("boardId") int boardId,
      @RequestParam(value = "subject", required = false) String subject,
      @RequestParam("content") String content, @RequestParam("anonymous") String anonymous,
      @RequestParam(value = "images", required = false) List<MultipartFile> files,
      HttpSession session) {
    Map<String, Object> result = new HashMap<>();

    // 업로드된 이미지가 있으면 폴더명에 사용할 userLoginId 가져오기
    String userLoginId = null;
    if (files != null) {
      userLoginId = (String) session.getAttribute("userLoginId");
    }

    // DB insert
    int row = postBO.addPost(boardId, (String) session.getAttribute("userNickname"), subject,
        content, anonymous, userLoginId, files);
    if (row != 0) {
      result.put("result", "success");
    } else {
      result.put("result", "글 저장을 실패했습니다. 다시 입력해주세요.");
    }

    return result;
  }

  /**
   * 글 삭제
   * 
   * @param id
   * @param session
   * @return
   */
  @DeleteMapping("/delete")
  public Map<String, Object> delete(
      @RequestParam("postId") int id,
      HttpSession session) {
    Map<String, Object> result = new HashMap<>();

    int row = postBO.deletePostByIdAndNickname(id, (String) session.getAttribute("userNickname"));
    if (row == 1) {
      result.put("result", "success");
    } else {
      result.put("result", "글 저장을 실패했습니다. 다시 입력해주세요.");
    }

    return result;
  }

}
