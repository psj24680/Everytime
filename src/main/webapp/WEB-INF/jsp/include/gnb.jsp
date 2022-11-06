<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="bg-light">
	<a href="/main/main_view" class="header-logo" style="text-decoration: none">
		<img alt="everytime-logo" src="/static/img/everytime-logo.png">
		<c:choose>
			<c:when test="${empty userSchool}">
				<span>에브리타임</span>
			</c:when>
			<c:otherwise>
				<span>${userSchool}</span>
			</c:otherwise>
		</c:choose>
	</a>

	<div class="header-menu">
		<c:choose>
			<c:when test="${empty userId}">
				<a href="/user/sign_in_view">로그인</a>
				<a href="/user/sign_up_view">회원가입</a>
			</c:when>
			<c:otherwise>
				<span>
					<strong>${userNickname}</strong>
					님, 안녕하세요!
				</span>
			</c:otherwise>
		</c:choose>
	</div>
</div>