package com.everytime.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everytime.comment.bo.CommentBO;
import com.everytime.comment.model.Comment;
import com.everytime.comment_comment.bo.CommentCommentBO;
import com.everytime.comment_comment.model.CommentComment;
import com.everytime.post.bo.PostBO;
import com.everytime.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostBO postBO;

	@Autowired
	private CommentBO commentBO;

	@Autowired
	private CommentCommentBO commentCommentBO;

	/**
	 * 내가 쓴 글 화면
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/my_post_view")
	public String myPostView(HttpSession session, Model model) {
		// 로그인 시 세션에 저장된 nickname으로 게시글 불러오기
		model.addAttribute("myPostList", postBO.getPostListByNickname((String) session.getAttribute("userNickname")));
		model.addAttribute("viewName", "post/my_post_view");

		return "template/layout";
	}

	/**
	 * 댓글 단 글 화면
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/my_comment_view")
	public String myCommentView(HttpSession session, Model model) {
		String nickname = (String) session.getAttribute("userNickname");
		Set<Integer> postIdHashSet = new HashSet<>();
		List<Post> myCommentPostList = new ArrayList<>();

		// 본인이 달았던 댓글들 불러오기
		List<Comment> commentList = commentBO.getCommentListByNickname(nickname);
		for (Comment comment : commentList) {
			postIdHashSet.add(comment.getPostId());
		}

		// 본인이 달았던 대댓글들 불러오기
		List<CommentComment> commentCommentList = commentCommentBO.getCommentCommentListByNickname(nickname);
		for (CommentComment commentComment : commentCommentList) {
			postIdHashSet.add(commentComment.getPostId());
		}

		// postId 내림차순 정렬 - HashSet을 List로 변환 후 변환
		List<Integer> postIdList = new ArrayList<>(postIdHashSet);
		Collections.sort(postIdList, Collections.reverseOrder());

		// postIdList에 저장된 postId로 PostList 만들기
		Iterator<Integer> iter = postIdList.iterator();
		while (iter.hasNext()) {
			myCommentPostList.add(postBO.getPostById(iter.next()));
		}

		model.addAttribute("myCommentPostList", myCommentPostList);
		model.addAttribute("viewName", "post/my_comment_view");

		return "template/layout";
	}
}
