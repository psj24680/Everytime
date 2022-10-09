package com.everytime.post.bo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.everytime.clipping.bo.ClippingBO;
import com.everytime.comment.bo.CommentBO;
import com.everytime.comment_comment.bo.CommentCommentBO;
import com.everytime.common.FileManagerService;
import com.everytime.like.bo.LikeBO;
import com.everytime.post.dao.PostDAO;
import com.everytime.post.model.Post;
import com.everytime.post.model.PostView;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private LikeBO likeBO;

	@Autowired
	private ClippingBO clippingBO;

	@Autowired
	private CommentBO commentBO;

	@Autowired
	private CommentCommentBO commentCommentBO;

	@Autowired
	private FileManagerService fileManager;

	public int addPost(int boardId, String nickname, String subject, String content, String anonymous, String userLoginId, MultipartFile file) {
		Map<String, Object> postMap = new HashMap<>();
		postMap.put("boardId", boardId);
		postMap.put("nickname", nickname);
		postMap.put("subject", subject);
		postMap.put("content", content);
		postMap.put("anonymous", anonymous);

		// 이미지 파일이 있을 때
		if (file != null) {
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
	 * boardId, postId로 PostView(게시글 상세 정보) 만들기
	 * 
	 * @param boardId
	 * @param postId
	 * @return
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

		// 스크랩 개수
		postView.setClippingCount(clippingBO.getClippingCountByPostId(postId));

		// 댓글, 대댓글 개수
		postView.setCommentCount(commentBO.getCommentCountByPostId(postId) + commentCommentBO.getCommentCommentCountByPostId(postId));

		return postView;
	}

	public List<Post> getPostListByNickname(String nickname) {
		return postDAO.selectPostListByNickname(nickname);
	}

	public int deletePostByIdAndNickname(int id, String nickname) {
		// 이미지 삭제
		String imagePath = postDAO.selectImagePathById(id);
		if (imagePath != null) {
			try {
				fileManager.deleteFile(imagePath);
				postDAO.deleteImagePathById(id);
			} catch (IOException e) {
				logger.error("[DELETE POST] 이미지 삭제 실패. postId: {}", id);
			}
		}

		// 댓글 삭제
		commentBO.deleteCommentByPostId(id);

		// 대댓글 삭제
		commentCommentBO.deleteCommentCommentByPostId(id);

		// 좋아요 삭제
		likeBO.deleteLikeByPostId(id);

		// 글 삭제
		return postDAO.deletePostByIdAndNickname(id, nickname);
	}

}
