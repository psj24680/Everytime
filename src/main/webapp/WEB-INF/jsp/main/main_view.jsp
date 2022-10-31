<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
						<a href="/user/my_page_view" class="my-page">내 정보</a>
						<a href="/user/sign_out" class="sign-out">로그아웃</a>
					</div>
				</div>
			</c:when>
		</c:choose>
		<div class="card2">
			<div class="left-menu">
				<img src="/static/img/my-post-list-icon.png" alt="내가 쓴 글">
				<a href="/post/my_post_view">내가 쓴 글</a>
			</div>
			<div class="left-menu">
				<img src="/static/img/my-comment-list-icon.png" alt="댓글 단 댓글">
				<a href="/post/my_comment_view">댓글 단 글</a>
			</div>
			<div class="left-menu">
				<img src="/static/img/my-clipping-list-icon.png" alt="내 스크랩">
				<a href="/post/my_clipping_view">내 스크랩</a>
			</div>
		</div>

		<div class="left-ad-card">
			<a href="https://event.multicampus.com/bigdatafullstack/#utm_source=everytime&utm_medium=display&utm_campaign=multi_fullstack&utm_content=221031&utm_term=10_fullstack" target="_blank">
				<img alt="광고" src="/static/img/everytime_left-side_ad_1.jpg" style="width: 100%">
			</a>
		</div>
		<div class="left-ad-card">
			<a href="https://www.aice.team/" target="_blank">
				<img alt="광고" src="/static/img/everytime_left-side_ad_2.jpg" style="width: 100%">
			</a>
		</div>
		<div class="left-ad-card">
			<a href="https://www.campushow.com/?utm_source=everytime&utm_medium=homecard220920&utm_campaign=vl_2022" target="_blank">
				<img alt="광고" src="/static/img/everytime_left-side_ad_3.png" style="width: 100%">
			</a>
		</div>
	</div>
	<div class="main">
		<div class="banner">
			<a href="https://nid.naver.com/membership/join?m=joinStudent&evt=MK1013&pcode=targetmedia_everytime_homebanner_cu" target="_blank">
				<img alt="광고" src="/static/img/everytime_ad.jpg" style="width: 100%">
			</a>
		</div>

		<div class="board-box flex-wrap">
			<c:forEach var="maps" items="${boardMap}">
				<div class="board-card">
					<h3>
						<a href="/board/${maps.value.board.id}">${maps.key}</a>
					</h3>
					
					<c:forEach var="post" items="${maps.value.recentPostList}">
						<c:choose>
							<c:when test="${maps.key eq '자유게시판'}">
								<a href="/board/${post.boardId}/post/${post.id}">
									<p>${post.subject}</p>
									<small><fmt:formatDate value="${post.createdAt}" pattern="MM/dd HH:mm" /></small>
								</a>
							</c:when>
							<c:otherwise>
								<a href="/board/${post.boardId}/post/${post.id}">
									<p>${post.content}</p>
									<small><fmt:formatDate value="${post.createdAt}" pattern="MM/dd HH:mm" /></small>
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="right-side"></div>
</div>