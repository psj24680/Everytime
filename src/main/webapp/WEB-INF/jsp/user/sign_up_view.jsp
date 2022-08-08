<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>

<!-- bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<!-- stylesheet -->
<link rel="stylesheet" href="/static/css/login_styles.css">
</head>
<body>
	<div id="wrap">
		<div class="sign-up">
			<div>
				<span>로그인 정보 입력</span>
				
				<input type="text" id="loginId" maxlength="20" placeholder="아이디 - 영문/숫자 4~20자">
				<input type="password" id="password" maxlength="20" placeholder="비밀번호 - 영문/특문/숫자 2종류 이상 8~20자">
				<input type="password" id="confirmPassword" maxlength="20" placeholder="비밀번호 확인">
				<input type="text" id="email" placeholder="이메일">
			</div>

			<div>
				<span>개인 정보 입력</span>
				
				<input type="text" id="nickname" placeholder="닉네임">
				<input type="text" id="school"  placeholder="학교">
				<input type="text" id="schoolId" placeholder="학번">
			</div>
			
			<button type="button" id="signUpBtn">회원가입</button>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('#signUpBtn').on('click', function() {
				// validation
				let loginId = $('#loginId').val().trim();
				if (loginId == "") {
					alert("아이디를 입력하세요.");
					return;
				}
	
				let password = $('#password').val().trim();
				let confirmPassword = $('#confirmPassword').val().trim();
				if (password == "" || confirmPassword == "") {
					alert("비밀번호를 입력하세요.");
					return;
				}
	
				if (password != confirmPassword) {
					alert("비밀번호가 일치하지 않습니다.");
					$('#password').val("");
					$('#confirmPassword').val("");
					return;
				}
				
				if (password.length < 8 || password.length > 20) {
					alert("비밀번호는 영문, 특문, 숫자가 2종류 이상 조합된 8~20자로 입력하세요.");
					return;
				}
				
				// 정규식 - https://heeya7.tistory.com/37
	
				let email = $('#email').val().trim();
				if (email == "") {
					alert("이메일을 입력하세요.");
					return;
				}
				
				let nickname = $('#nickname').val().trim();
				if (nickname == "") {
					alert("닉네임을 입력하세요.");
					return;
				}
				
				let school = $('#school').val().trim();
				if (school == "") {
					alert("학교를 입력하세요.");
					return;
				}
				
				let schoolId = $('#schoolId').val().trim();
				if (schoolId == "") {
					alert("학번을 입력하세요.");
					return;
				}
			});
		});
	</script>
</body>
</html>