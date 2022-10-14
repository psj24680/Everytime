<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="col-4">
	<table class="table table-striped table-bordered my-info-table">
	    <thead>
	        <tr>
	            <th colspan="2">내 정보</th>
	        </tr>
	    </thead>
	    <tbody>
	        <tr>
	            <td>ID</td>
	            <td>${user.loginId}</td>
	        </tr>
	        <tr>
	            <td>Password</td>
	            <td>
	            	<button class="btn btn-success">변경</button>
	            </td>
	        </tr>
	        <tr>
	            <td>이름</td>
	            <td>${user.name}</td>
	        </tr>
	        <tr>
	            <td>이메일</td>
	            <td>${user.email}</td>
	        </tr>
	        <tr>
	            <td>닉네임</td>
	            <td>${user.nickname}</td>
	        </tr>
	        <tr>
	            <td>학교</td>
	            <td>${user.school}</td>
	        </tr>
	        <tr>
	            <td>학번</td>
	            <td>${user.schoolId}</td>
	        </tr>
	        <tr>
	            <td>학교 메일</td>
	            <td>
	            	<c:if test="${empty user.schoolEmail}">
	            		학교 인증을 해주세요.
	            	</c:if>
	            </td>
	        </tr>
	    </tbody>
	</table>
</div>