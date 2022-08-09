<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
		<div class="sign-in">
			<h1 class="logo">
				<a href="#">
					<img src="/static/assets/img/everytime-logo.png" alt="logo">
				</a>
				<span>지금 <strong>에브리타임</strong>을 시작하세요!</span>
			</h1>

			<input type="text" id="loginId" maxlength="20" placeholder="아이디">

			<input type="password" id="password" maxlength="20" placeholder="비밀번호">

			<button type="button" id="signInBtn">로그인</button>

			<div id="register">
				<span>에브리타임이 처음이신가요?</span> <a href="/user/sign_up_view">회원가입</a>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			$('#signInBtn').on('click', function() {
				// validation
				let loginId = $('#loginId').val().trim();
				if (loginId == "") {
					alert("아이디를 입력하세요.");
					return;
				}

				let password = $('#password').val();
				if (password == "") {
					alert("비밀번호를 입력하세요.");
					return;
				}

				// AJAX - DB select
				$.ajax({
					type : "POST",
					url : "/user/sign_in",
					data : {
						"loginId" : loginId,
						"password" : password
					},
					success : function(data) {
						if (data.result == "success") {
							// 로그인 성공
							location.href = "/user/test";
						} else {
							alert(data.result);
						}
					},
					error : function(e) {
						alert("로그인 중 오류 발생");
					}
				});
			});
		});
	</script>
</body>
</html>