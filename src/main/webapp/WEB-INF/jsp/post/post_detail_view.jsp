<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<!-- 게시판 제목 -->
	<div class="board-title">
		<a href="/board/${boardId}">${board.name}</a>
	</div>

	<!-- 게시글 -->
	<article>
		<!-- 게시글 내용 -->
		<div class="post-box">
			<div class="post-profile-box">
				<img alt="user-icon" src="/static/img/user-icon.png">

				<div>
					<h3>
						<c:choose>
							<c:when test="${post.anonymous eq 'O'}">
								익명
							</c:when>
							<c:when test="${post.anonymous eq 'X'}">
								${post.userId}
							</c:when>
						</c:choose>
					</h3>
					<span>
						<fmt:formatDate value="${post.createdAt}" pattern="MM/dd HH:mm" />
					</span>
				</div>
			</div>

			<h2>${post.subject}</h2>
			<p>${post.content}</p>

			<ul class="status">
				<li title="공감" class="like">0</li>
				<li title="댓글" class="comment">0</li>
				<li title="스크랩" class="clipping">0</li>
			</ul>

			<div class="buttons">
				<button type="button" class="like-btn">공감</button>
				<button type="button" class="clipping-btn">스크랩</button>
			</div>
		</div>

		<!-- 게시글 댓글 입력창-->
		<div class="comment-box" style="display: block">
			<input type="text" id="comment" maxlength="300" autocomplete="off" placeholder="댓글을 입력하세요.">

			<ul class="option">
				<li title="완료" class="save"></li>
				<li title="익명" class="anonymous"></li>
			</ul>
		</div>
	</article>
</div>

<script>
	$(document).ready(function() {
		// 익명 여부 확인
		$('.anonymous').on('click', function() {
			if ($('.anonymous').hasClass('active')) {
				$('.anonymous').removeClass('active');
			} else {
				$('.anonymous').addClass('active');
			}
		});

		$('.save').on('click', function(e) {
			// 창이 올라가는 것을 방지
			e.preventDefault();

			// validation
			let comment = $('#comment').val();
			if (comment == "") {
				alert("내용을 입력하세요.");
				return;
			}

			let anonymous = null;
			if ($('.anonymous').hasClass('active')) {
				anonymous = "O";
			} else {
				anonymous = "X";
			}

			let boardId = ${boardId};
			let postId = ${post.id};
			let userId = ${userId};
			// alert("comment: " + comment + "\nanonymous: " + anonymous + "\nboardId: " + boardId + "\npostId: " + postId + "\nuserId: " + userId);

			$.ajax({
				type : "POST",
				url : "/comment/create",
				data : {
					"boardId" : boardId,
					"postId" : postId,
					"userId" : userId,
					"content" : comment,
					"anonymous" : anonymous
				},
				success : function(data) {
					if (data.result == "success") {
						location.reload(true);
					} else {
						alert(data.result);
					}
				},
				error : function(e) {
					alert("댓글 저장 중 오류 발생");
				}
			})
		});
	});
</script>