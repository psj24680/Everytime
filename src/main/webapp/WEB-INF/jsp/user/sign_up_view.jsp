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
	<div class="campusList d-none mx-3">
		<!-- 결과가 출력될 곳 -->
	</div>

	<div id="wrap">
		<div class="sign-up">
			<div>
				<span>로그인 정보 입력</span>
				<input type="text" id="loginId" maxlength="20" placeholder="아이디 - 영문/숫자 4~20자">
				<input type="password" id="password" maxlength="20" placeholder="비밀번호 - 영문/특문/숫자 2종류 이상 8~20자">
				<input type="password" id="confirmPassword" maxlength="20" placeholder="비밀번호 확인"> <input type="text" id="email" placeholder="이메일">
			</div>

			<div>
				<span>개인 정보 입력</span>
				<input type="text" id="nickname" maxlength="10" placeholder="닉네임">
				<input type="text" id="school" placeholder="학교" readonly="readonly">
				<input type="text" id="schoolId" placeholder="학번">
			</div>

			<button type="button" id="signUpBtn">회원가입</button>
			
			<div id="register">
				<span>이미 계정이 있으신가요?</span>
				<a href="/user/sign_in_view">로그인</a>
			</div>
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
				
				// 정규식
				let existKorById = loginId.search(/[ㄱ-ㅎㅏ-ㅣ가-힣]/g);
				let existNumById = loginId.search(/[0-9]/g);
				if ((existKorById == 0) || (existNumById < 0) || loginId.length < 4) {
					alert("아이디는 영어, 숫자가 조합된 4~20자를 입력하세요.");
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

				// 정규식
				let existNumByPw = password.search(/[0-9]/g);
				let existEngByPw = password.search(/[a-z]/ig);
				let existSpeByPw = password.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
				if ((existNumByPw < 0 && existEngByPw < 0) || (existEngByPw < 0 && existSpeByPw < 0) || (existSpeByPw < 0 && existNumByPw < 0) || password.length < 8) {
					alert("비밀번호는 영문, 숫자, 특문이 2개 이상 조합된 8~20자를 입력하세요.");
					return;
				}

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
				
				if (nickname.length > 10) {
					alert("닉네임은 10자 이내로 입력하세요.");
					return
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
				
				// AJAX - DB insert
				$.ajax({
					type : "POST",
					url : "/user/sign_up",
					data : {
						"loginId" : loginId,
						"password" : password,
						"email" : email,
						"nickname" : nickname,
						"school" : school,
						"schoolId" : schoolId
					},
					success : function(data) {
						if (data.result == "success") {
							// 회원가입 성공
							alert("환영합니다. 로그인 페이지로 이동합니다.");
							location.href = "/user/sign_in_view";
						} else {
							alert(data.result);
						}
					},
					error : function(e) {
						alert("회원가입 중 오류 발생");
					}
				});
			});
			
			$('#school').on('click', function(e) {
				$.ajax({
					method: "get",
				    url: "https://www.career.go.kr/cnet/openapi/getOpenApi.json?apiKey=4b18c9c07295ede3beb8730178d80acc",
				    data: {
				    	"svcType" : "api",
				    	"svcCode" : "SCHOOL",
				    	"contentType" : "json",
				    	"gubun" : "univ_list",
				    	"perPage" : "500",
				    },
					success : function(data) {
						console.log('data: ' + data);
						console.log('data.length: ' + data.dataSearch.content.length);
						
						for (var i = 0; i < data.dataSearch.content.length; i++) {
							if (data.dataSearch.content[i].campusName == '본교') {
								$('.campusList').append("<a href='#' class='school-name-btn' data-school-name='" 
										+ data.dataSearch.content[i].schoolName 
										+ "'>" 
										+ "<span>"
										+ data.dataSearch.content[i].schoolName 
										+ "</span>"
										+ "</a>");
							}
						}
						
						$('.campusList').removeClass('d-none');
					},
					error : function(e) {
						alert("데이터 불러오기 중 오류 발생");
					}
				});
			});
			
			$('.school-name-btn').on('click', function(e) {
				e.preventDefault();
				
				alert('test');
				
				let schoolName = $(this).data('school-name');
				
				alert(schoolName);
			});
		});
	</script>
</body>
</html>