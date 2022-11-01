<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<div>
	<%-- 게시판 제목 --%>
	<div class="ps-board-title">
		<a href="/board/${board.id}">${board.name}</a>
	</div>

	<%-- 게시글 --%>
	<article>
		<%-- 게시글 내용 --%>
		<div class="ps-post">
			<div class="ps-profile">
				<div>
					<img alt="user-icon" src="/static/img/user-icon.png">

					<div>
						<c:choose>
							<c:when test="${postView.post.anonymous eq 'O'}">
								<h3>익명</h3>
							</c:when>
							<c:when test="${postView.post.anonymous eq 'X'}">
								<h3>${postView.post.nickname}</h3>
							</c:when>
						</c:choose>
						<span>
							<fmt:formatDate value="${postView.post.createdAt}" pattern="MM/dd HH:mm" />
						</span>
					</div>
				</div>

				<c:if test="${postView.post.nickname eq userNickname}">
					<ul class="ps-comments-status">
						<li class="post-delete-btn" data-board-id="${board.id}" data-post-id="${postView.post.id}">삭제</li>
					</ul>
				</c:if>
			</div>

			<c:if test="${board.id eq 1}">
				<h2>${postView.post.subject}</h2>
			</c:if>
			<p class="test">${fn:replace(postView.post.content, replaceChar, "<br>")}</p>

			<div class="ps-image">
				<c:if test="${not empty postView.imagePath}">
					<c:forEach var="imagePath" items="${postView.imagePath}">
						<img alt="uploaded-image" src="${imagePath}" class="uploaded-image" data-toggle="modal" data-target="#modal" data-image-path="${imagePath}">
						<script>
							// 이미지 경로 가져와서 modal창으로 보내기
							$('.uploaded-image').on('click', function() {
								let imagePath = $(this).data('image-path');
								$('.full-image').attr('src', imagePath);
							});
						</script>
					</c:forEach>
				</c:if>
			</div>

			<ul class="ps-post-status">
				<li title="좋아요" class="post-like-count">${postView.likeCount}</li>
				<li title="댓글" class="post-comment-count">${postView.commentCount}</li>
				<li title="스크랩" class="post-clipping-count">${postView.clippingCount}</li>
			</ul>

			<div class="ps-post-buttons">
				<button type="button" class="post-like-btn" data-board-id="${postView.post.boardId}" data-post-id="${postView.post.id}" data-user-id="${userId}">공감</button>
				<button type="button" class="post-clipping-btn" data-board-id="${postView.post.boardId}" data-post-id="${postView.post.id}" data-user-id="${userId}">스크랩</button>
			</div>
		</div>

		<%-- 댓글 --%>
		<c:forEach var="commentView" items="${postView.commentViewList}">
			<div>
				<div class="ps-comments">
					<div>
						<div class="ps-comments-profile">
							<img alt="user-icon" src="/static/img/user-icon.png">

							<c:choose>
								<c:when test="${commentView.comment.isDeleted eq 1}">
									<h3 style="color: #a6a6a6">(삭제)</h3>
								</c:when>

								<%-- 익명 댓글 --%>
								<c:when test="${commentView.comment.anonymous eq 'O'}">
									<%-- 작성자 여부 확인 --%>
									<c:choose>
										<c:when test="${commentView.comment.nickname eq postView.post.nickname}">
											<h3 class="writer">익명(글쓴이)</h3>
										</c:when>
										<c:otherwise>
											<h3>익명</h3>
										</c:otherwise>
									</c:choose>
								</c:when>

								<%-- 비익명 댓글 --%>
								<c:when test="${commentView.comment.anonymous eq 'X'}">
									<h3>${commentView.comment.nickname}</h3>
								</c:when>
							</c:choose>
						</div>

						<c:if test="${commentView.comment.isDeleted ne 1}">
							<ul class="ps-comments-status">
								<li class="comment-comment-btn">대댓글</li>
								<c:if test="${userNickname eq commentView.comment.nickname}">
									<li class="comment-delete-btn" data-comment-id="${commentView.comment.id}">삭제</li>
								</c:if>
							</ul>
						</c:if>
					</div>

					<%-- 댓글 내용 --%>
					<c:choose>
						<c:when test="${commentView.comment.isDeleted eq 1}">
							<p>삭제된 댓글입니다.</p>
						</c:when>
						<c:otherwise>
							<p>${commentView.comment.content}</p>
						</c:otherwise>
					</c:choose>

					<%-- 댓글 작성일 --%>
					<span>
						<c:if test="${commentView.comment.isDeleted ne 1}">
							<fmt:formatDate value="${commentView.comment.createdAt}" pattern="MM/dd HH:mm" />
						</c:if>
					</span>
				</div>

				<%-- 대댓글 --%>
				<c:forEach var="commentComment" items="${commentView.comment_comment}">
					<div class="ps-comment-comment">
						<div>
							<div class="ps-comment-comment-profile">
								<img alt="user-icon" src="/static/img/user-icon.png">

								<c:choose>
									<c:when test="${commentComment.anonymous eq 'O'}">
										<c:choose>
											<c:when test="${commentComment.nickname eq postView.post.nickname}">
												<h3 class="writer">익명(글쓴이)</h3>
											</c:when>
											<c:otherwise>
												<h3>익명</h3>
											</c:otherwise>
										</c:choose>
									</c:when>
									<c:when test="${commentComment.anonymous eq 'X'}">
										<h3>${commentComment.nickname}</h3>
									</c:when>
								</c:choose>
							</div>

							<ul class="ps-comments-status">
								<c:if test="${userNickname eq commentComment.nickname}">
									<li class="commentComment-delete-btn" data-comment-comment-id="${commentComment.id}">삭제</li>
								</c:if>
							</ul>
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
						<li title="완료" class="comment-comment-save" data-board-id="${commentView.comment.boardId}" data-post-id="${commentView.comment.postId}" data-comment-id="${commentView.comment.id}"></li>
					</ul>
				</div>
			</div>
		</c:forEach>


		<%-- 댓글 입력창 --%>
		<div class="ps-comment-write">
			<input type="text" id="comment" maxlength="300" autocomplete="off" placeholder="댓글을 입력하세요.">

			<ul class="option">
				<li title="익명" class="comment-anonymous"></li>
				<li title="완료" class="comment-save" data-board-id="${boardId}" data-post-id="${postView.post.id}"></li>
			</ul>
		</div>
	</article>
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<div class="modal-dialog modal-dialog-centered modal-lg">
		<div class="modal-content">
			<div class="text-center" data-dismiss="modal">
				<img alt="full-size-uploaded-image" src="" class="full-image w-100">
				<div class="py-3">
					<a href="#">취소</a>
				</div>
			</div>
		</div>
	</div>
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

		// 스크랩
		$('.post-clipping-btn').on('click', function() {
			let boardId = $(this).data('board-id');
			let postId = $(this).data('post-id');
			let userId = $(this).data('user-id');

			$.ajax({
				type : "POST",
				url : "/clipping",
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
					alert("스크랩 중 오류 발생");
				}
			});
		});

		// 댓글 익명 여부
		$('.comment-anonymous').on('click', function() {
			if ($('.comment-anonymous').hasClass('active')) {
				$('.comment-anonymous').removeClass('active');
			} else {
				$('.comment-anonymous').addClass('active');
			}
		});

		// 댓글 저장
		$('.comment-save').on('click', function(e) {
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

			let boardId = $(this).data('board-id');
			let postId = $(this).data('post-id');

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

		// 대댓글 입력창 띄우기
		$('.comment-comment-btn').on('click', function() {
			if ($(this).parent().parent().parent().nextAll('.ps-comment-comment-write').hasClass('d-none')) {
				$(this).parent().parent().parent().nextAll('.ps-comment-comment-write').removeClass('d-none');
			} else {
				$(this).parent().parent().parent().nextAll('.ps-comment-comment-write').addClass('d-none');
			}
		});

		// 대댓글 익명 여부
		$('.comment-comment-anonymous').on('click', function() {
			if ($('.comment-comment-anonymous').hasClass('active')) {
				$('.comment-comment-anonymous').removeClass('active');
			} else {
				$('.comment-comment-anonymous').addClass('active');
			}
		});

		// 대댓글 저장
		$('.comment-comment-save').on('click', function(e) {
			e.preventDefault();

			// validation
			let boardId = $(this).data('board-id')
			let postId = $(this).data('post-id');
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

		// 글 삭제
		$('.post-delete-btn').on('click', function() {
			let boardId = $(this).data('board-id');
			let postId = $('.post-delete-btn').data('post-id');

			$.ajax({
				type : "DELETE",
				url : "/post/delete",
				data : {
					"postId" : postId
				},
				success : function(data) {
					if (data.result == "success") {
						location.href = "/board/" + boardId;
					} else {
						alert(data.result);
						location.href = "/user/sign_in_view";
					}
				},
				error : function(e) {
					alert("글 삭제 중 오류 발생");
				}
			});
		});

		// 댓글 삭제
		$('.comment-delete-btn').on('click', function() {
			let commentId = $('.comment-delete-btn').data('comment-id');

			$.ajax({
				type : "DELETE",
				url : "/comment/delete",
				data : {
					"commentId" : commentId
				},
				success : function(data) {
					if (data.result == "success") {
						location.reload(true);
					} else {
						alert(data.result);
					}
				},
				error : function(e) {
					alert("댓글 삭제 중 오류 발생");
				}
			});
		});

		// 대댓글 삭제
		$('.commentComment-delete-btn').on('click', function() {
			let commentCommentId = $('.commentComment-delete-btn').data('comment-comment-id');

			$.ajax({
				type : "DELETE",
				url : "/comment_comment/delete",
				data : {
					"commentCommentId" : commentCommentId
				},
				success : function(data) {
					if (data.result == "success") {
						location.reload(true);
					} else {
						alert(data.result);
					}
				},
				error : function(e) {
					alert("대댓글 삭제 중 오류 발생");
				}
			});
		});
	});
</script>