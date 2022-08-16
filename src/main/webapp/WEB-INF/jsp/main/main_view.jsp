<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex">
	<div class="left-side bg-light">
		<c:choose>
			<c:when test="${empty userId}">
				<!-- 로그인 상태만 접근 가능 -->
				<div class="logged-in">
					<span> 커뮤니티 이용을 위해<br> <strong>로그인</strong>이 필요합니다.</span>
					<a href="/user/sign_in_view" class="btn sign-in-btn">로그인</a>
					<a href="/user/sign_up_view" class="btn sign-up-btn">회원가입</a>
				</div>
			</c:when>
			<c:when test="${not empty userId}">
				<!-- 로그아웃 상태 -->
				<div class="logged-out">
					<img src="/static/img/user-icon.png" alt="유저">

					<p class="nickname">${userNickname}</p>
					<p class="school">이름</p>
					<p class="school">${userSchoolId}</p>

					<div>
						<a href="#" class="my-page">내 정보</a>
						<a href="/user/sign_out" class="sign-out">로그아웃</a>
					</div>
				</div>
			</c:when>
		</c:choose>
		<div class="card2">
			<div class="left-menu">
				<img src="/static/img/my-post-list-icon.png" alt="내가 쓴 글">
				<a href="">내가 쓴 글</a>
			</div>
			<div class="left-menu">
				<img src="/static/img/my-comment-list-icon.png" alt="댓글 단 댓글">
				<a href="">댓글 단 글</a>
			</div>
			<div class="left-menu">
				<img src="/static/img/my-clipping-list-icon.png" alt="내 스크랩">
				<a href="">내 스크랩</a>
			</div>
		</div>

		<div class="left-ad-card"></div>
		<div class="left-ad-card"></div>
		<div class="left-ad-card"></div>
	</div>
	<div class="main">
		<div class="banner"></div>

		<div class="board-box flex-wrap">
			<c:forEach var="board" items="${boardList}">
				<div class="board-card">
					<a href="/board/${board.id}">${board.name}</a>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="right-side"></div>
</div>