<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="m-3">
	<%-- 게시판 제목 --%>
	<div class="ps-board-title">
		<a href="#">자유게시판</a>
	</div>

	<%-- 게시글 --%>
	<article>
		<%-- 게시글 내용 --%>
		<div class="ps-post">
			<div class="ps-profile">
				<img alt="user-icon" src="/static/img/user-icon.png">

				<div>
					<c:choose>
						<c:when test="${postView.post.anonymous eq 'O'}">
							<h3>익명</h3>
						</c:when>
						<c:when test="${postView.post.anonymous eq 'X'}">
							<h3>${postView.user.nickname}</h3>
						</c:when>
					</c:choose>
					<span>
						<fmt:formatDate value="${postView.post.createdAt}" pattern="MM/dd HH:mm" />
					</span>
				</div>
			</div>

			<h2>${postView.post.subject}</h2>
			<p>${postView.post.content}</p>
			
			<c:if test="${not empty postView.imagePath}">
				<img alt="uploaded-image" src="${postView.imagePath}" class="uploaded-image">
			</c:if>

			<ul class="ps-post-status">
				<li title="좋아요" class="post-like-count">${postView.likeCount}</li>
				<li title="댓글" class="post-comment-count">0</li>
				<li title="스크랩" class="post-clipping-count">0</li>
			</ul>

			<div class="ps-post-buttons">
				<button type="button" class="post-like-btn" data-board-id="${postView.post.boardId}" data-post-id="${postView.post.id}" data-user-id="${userId}">공감</button>
				<button type="button" class="post-clipping-btn">스크랩</button>
			</div>
		</div>

		<%-- 댓글 --%>
		<c:forEach var="commentView" items="${postView.commentViewList}">
			<div class="ps-comments">
				<div>
					<div class="ps-comments-profile">
						<img alt="user-icon" src="/static/img/user-icon.png">
	
						<c:choose>
							<c:when test="${commentView.comment.anonymous eq 'O'}">
								<h3>익명</h3>
							</c:when>
							<c:when test="${commentView.comment.anonymous eq 'X'}">
								<h3>${commentView.user.nickname}</h3>
							</c:when>
					</c:choose>
					</div>
	
					<ul class="ps-comments-status">
						<li class="comment-comment-btn">대댓글</li>
						<li class="comment-delete-btn">삭제</li>
					</ul>
				</div>
				<p>${commentView.comment.content}</p>
				<span>
					<fmt:formatDate value="${commentView.comment.createdAt}" pattern="MM/dd HH:mm" />
				</span>
			</div>
	
			<%-- 대댓글 --%>
			<c:forEach var="commentComment" items="${commentView.comment_comment}">
				<div class="ps-comment-comment">
					<div>
						<img alt="user-icon" src="/static/img/user-icon.png">
		
						<h3>${commentComment.userId}</h3>
					</div>
					<p>${commentComment.content}</p>
					<span>
						<fmt:formatDate value="${commentComment.createdAt}" pattern="MM/dd HH:mm" />
					</span>
				</div>
			</c:forEach>
			
			<%-- 대댓글 입력창 --%>
			<div class="ps-comment-comment-write d-none">
				<input type="text" id="commentComment" name="commentComment" maxlength="300" autocomplete="off" placeholder="대댓글을 입력하세요.">
	
				<ul class="option">
					<li title="익명" class="comment-comment-anonymous"></li>
					<li title="완료" class="comment-comment-save" data-comment-id="${commentView.comment.id}"></li>
				</ul>
			</div>
		</c:forEach>

		<%-- 댓글 입력창 --%>
		<div class="ps-comment-write">
			<input type="text" id="comment" maxlength="300" autocomplete="off" placeholder="댓글을 입력하세요.">

			<ul class="option">
				<li title="익명" class="comment-anonymous"></li>
				<li title="완료" class="comment-save"></li>
			</ul>
		</div>
	</article>
</div>

<script>
	$(document).ready(function() {
		// 좋아요
		$('.post-like-btn').on('click', function() {
			let boardId = $(this).data('board-id');
			let postId = $(this).data('post-id');
			let userId = $(this).data('user-id');
			
			$.ajax({
				type : "POST",
				url : "/like",
				data : {
					"boardId" : boardId,
					"postId" : postId,
					"userId" : userId
				},
				success : function(data) {
					if (data.result == "success") {
						location.reload(true);
					}
				},
				error : function(e) {
					alert("좋아요 중 오류 발생");
				}
			});
		});
		
		// 댓글 익명
		$('.comment-anonymous').on('click', function() {
			if ($('.comment-anonymous').hasClass('active')) {
				$('.comment-anonymous').removeClass('active');
			} else {
				$('.comment-anonymous').addClass('active');
			}
		});

		// 댓글 저장
		$('.comment-save').on('click', function(e) {
			// 창이 올라가는 것을 방지
			e.preventDefault();

			// validation
			let comment = $('#comment').val();
			if (comment == "") {
				alert("댓글 내용을 입력하세요.");
				return;
			}

			let anonymous = null;
			if ($('.comment-anonymous').hasClass('active')) {
				anonymous = "O";
			} else {
				anonymous = "X";
			}

			let boardId = ${boardId};
			let postId = ${postView.post.id};

			$.ajax({
				type : "POST",
				url : "/comment/create",
				data : {
					"boardId" : boardId,
					"postId" : postId,
					"content" : comment,
					"anonymous" : anonymous
				},
				success : function(data) {
					if (data.result == "success") {
						location.reload(true);
					} else {
						alert(data.result);
						location.href = "/user/sign_in_view";
					}
				},
				error : function(e) {
					alert("댓글 저장 중 오류 발생");
				}
			})
		});

		// 대댓글 버튼 클릭
		$('.comment-comment-btn').on('click', function() {
			if ($('.ps-comment-write').prev().hasClass('d-none')) {
				$('.ps-comment-write').prev().removeClass('d-none');
				// alert("대댓글 창 띄움");
			} else {
				$('.ps-comment-write').prev().addClass('d-none');
				// alert("대댓글 창 없앰");
			}
		});

		// 대댓글 익명
		$('.comment-comment-anonymous').on('click', function() {
			if ($('.comment-comment-anonymous').hasClass('active')) {
				$('.comment-comment-anonymous').removeClass('active');
			} else {
				$('.comment-comment-anonymous').addClass('active');
			}
		});

		// 대댓글 저장
		$('.comment-comment-save').on('click', function(e) {
			// 창이 올라가는 것을 방지
			e.preventDefault();

			// validation
			let boardId = ${boardId};
			let postId = ${postView.post.id};
			let commentId = $(this).data('comment-id');

			let commentComment = $(this).parent().parent().find('#commentComment').val();
			if (commentComment == "") {
				alert("대댓글 내용을 입력하세요.");
				return;
			}

			let anonymous = null;
			if ($('.comment-comment-anonymous').hasClass('active')) {
				anonymous = "O";
			} else {
				anonymous = "X";
			}

			// alert("[     대댓글 DB insert     ]" + "\nCommentId: " + commentId + "\nComment-Comment: " + commentComment + "\nComment-Comment Anonymous: " + anonymous);
			$.ajax({
				type : "POST",
				url : "/comment_comment/create",
				data : {
					"boardId" : boardId,
					"postId" : postId,
					"commentId" : commentId,
					"content" : commentComment,
					"anonymous" : anonymous
				},
				success : function(data) {
					if (data.result == "success") {
						location.reload(true);
					} else {
						alert(data.result);
						location.href = "/user/sign_in_view";
					}
				},
				error : function(e) {
					alert("댓글 저장 중 오류 발생");
				}
			})
		});
	});
</script>