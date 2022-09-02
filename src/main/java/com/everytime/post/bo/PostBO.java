package com.everytime.post.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.everytime.comment.bo.CommentBO;
import com.everytime.common.FileManagerService;
import com.everytime.post.dao.PostDAO;
import com.everytime.post.model.Post;
import com.everytime.post.model.PostView;
import com.everytime.user.bo.UserBO;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private UserBO userBO;

	@Autowired
	private CommentBO commentBO;

	@Autowired
	private FileManagerService fileManager;

	String imagePath = null;

	public int addPost(int boardId, int userId, String subject, String content, String anonymous, String userLoginId, MultipartFile file) {
		// 이미지 파일이 있을 경우
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}

		Map<String, Object> postMap = new HashMap<>();
		postMap.put("boardId", boardId);
		postMap.put("userId", userId);
		postMap.put("subject", subject);
		postMap.put("content", content);
		postMap.put("anonymous", anonymous);
		postMap.put("imagePath", imagePath);

		// DB insert를 하고 왔기에 id가 채워진 상태
		postDAO.insertPost(postMap);

		return postDAO.insertImagePath(postMap);
	}

	public List<Post> getPostListByBoardId(int boardId) {
		return postDAO.selectPostListByBoardId(boardId);
	}

	public Post getPostById(int id) {
		return postDAO.selectPostById(id);
	}

	public String getImagePathById(int id) {
		return postDAO.selectImagePathById(id);
	}

	/**
	 * boardId, postId로 게시글 상세 정보 가져오기
	 * 
	 * @param boardId
	 * @param postId
	 * @return PostView
	 */
	public PostView generatePostViewByBoardIdAndPostId(int boardId, int postId) {
		// return 할 PostView 생성
		PostView postView = new PostView();

		// 글 정보
		Post post = getPostById(postId);
		postView.setPost(post);

		// 이미지
		postView.setImagePath(getImagePathById(postId));

		// 글쓴이 정보
		postView.setUser(userBO.getUserById(post.getUserId()));

		// 댓글 정보
		postView.setCommentViewList(commentBO.generateCommentViewListByPostId(postId));

		// 좋아요, 스크랩

		return postView;
	}

}
