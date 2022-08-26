package com.everytime.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.comment.dao.CommentDAO;
import com.everytime.comment.model.Comment;
import com.everytime.comment.model.CommentView;
import com.everytime.comment_comment.bo.CommentCommentBO;
import com.everytime.user.bo.UserBO;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private UserBO userBO;

	@Autowired
	private CommentCommentBO commentCommentBO;

	public int addComment(int boardId, int postId, int userId, String content, String anonymous) {
		return commentDAO.insertComment(boardId, postId, userId, content, anonymous);
	}

	public List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}

	public List<CommentView> generateCommentViewListByPostId(int postId) {
		// return 할 List<CommentView> 생성
		List<CommentView> commentViewList = new ArrayList<>();

		// 댓글 목록
		List<Comment> commentList = getCommentListByPostId(postId);
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();

			// 댓글 정보
			commentView.setComment(comment);

			// 댓글쓴이 정보 - comment의 userId로 user정보를 가져옴.
			commentView.setUser(userBO.getUserById(comment.getUserId()));

			// 대댓글들 정보 - commentCommentBO로 commentId보내면 BO에서 return
			int commentId = comment.getId();
			commentView.setComment_comment(commentCommentBO.getCommentCommentListByCommentId(commentId));

			// 리스트에 카드 저장
			commentViewList.add(commentView);
		}

		return commentViewList;
	}

}
