<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<div class="board-title">
		<a href="#" onClick="window.location.reload()">내 스크랩</a>
	</div>

	<div class="post">
		<c:forEach var="post" items="${myClippingList}">
			<a href="/board/${post.boardId}/post/${post.id}">
				<c:choose>
					<c:when test="${post.boardId eq 1}">
						<p class="post-subject">${post.subject}</p>
						<p class="post-content">${post.content}</p>
						<small class="post-time">
							<fmt:formatDate value="${post.createdAt}" pattern="MM/dd HH:mm" />
						</small>
						<div class="d-flex">
							<c:choose>
								<c:when test="${post.anonymous eq 'O'}">
									<p class="post-user">익명</p>
								</c:when>
								<c:when test="${post.anonymous eq 'X'}">
									<p class="post-user">${post.nickname}</p>
								</c:when>
							</c:choose>
							<div>
								<%-- 좋아요, 댓글, 스크랩 수 --%>
							</div>
						</div>
					</c:when>
					<c:when test="${post.boardId ne 1}">
						<p class="post-subject">${post.content}</p>
						<br>
						<small class="post-time">
							<fmt:formatDate value="${post.createdAt}" pattern="MM/dd HH:mm" />
						</small>
						<div class="d-flex">
							<c:choose>
								<c:when test="${post.anonymous eq 'O'}">
									<p class="post-user">익명</p>
								</c:when>
								<c:when test="${post.anonymous eq 'X'}">
									<p class="post-user">${post.nickname}</p>
								</c:when>
							</c:choose>
							<div>
								<%-- 좋아요, 댓글, 스크랩 수 --%>
							</div>
						</div>
					</c:when>
				</c:choose>
			</a>
		</c:forEach>
	</div>
</div>