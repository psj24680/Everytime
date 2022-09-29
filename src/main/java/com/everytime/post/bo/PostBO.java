package com.everytime.post.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.everytime.comment.bo.CommentBO;
import com.everytime.common.FileManagerService;
import com.everytime.like.bo.LikeBO;
import com.everytime.post.dao.PostDAO;
import com.everytime.post.model.Post;
import com.everytime.post.model.PostView;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private CommentBO commentBO;

	@Autowired
	private LikeBO likeBO;

	@Autowired
	private FileManagerService fileManager;

	public int addPost(int boardId, String nickname, String subject, String content, String anonymous, String userLoginId, MultipartFile file) {
		Map<String, Object> postMap = new HashMap<>();
		postMap.put("boardId", boardId);
		postMap.put("nickname", nickname);
		postMap.put("subject", subject);
		postMap.put("content", content);
		postMap.put("anonymous", anonymous);

		if (file != null) {
			// 이미지 파일이 있을 때
			String imagePath = fileManager.saveFile(userLoginId, file);

			postMap.put("imagePath", imagePath);

			postDAO.insertPost(postMap);

			return postDAO.insertImagePath(postMap);
		} else {
			return postDAO.insertPost(postMap);
		}
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

		// 댓글 정보
		postView.setCommentViewList(commentBO.generateCommentViewListByPostId(postId));

		// 좋아요 개수
		postView.setLikeCount(likeBO.getLikeCountByPostId(postId));

		// 댓글 개수

		// 스크랩 개수

		return postView;
	}

}
