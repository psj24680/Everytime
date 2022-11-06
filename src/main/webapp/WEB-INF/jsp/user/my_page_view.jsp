<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<a href="#" class="my-password-btn text-primary font-weight-bold" data-toggle="modal" data-target="#modal">변경</a>
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
				<td>${user.schoolEmail}</td>
			</tr>
		</tbody>
	</table>
</div>

<!-- Modal -->
<div class="modal fade" id="modal">
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content">
			<div class="text-center">
				<div class="py-3 border-bottom">
					<input type="password" id="currentPassword" class="form-control" placeholder="기존 비밀번호">
				</div>
				<div class="py-3 border-bottom">
					<input type="password" id="password" class="form-control" placeholder="비밀번호 - 영문/특문/숫자 2종류 이상 8~20자">
				</div>
				<div class="py-3 border-bottom">
					<input type="password" id="confirmPassword" class="form-control" placeholder="비밀번호 확인">
				</div>
				<div class="py-3" style="display: flex;">
					<%-- data-dismiss="modal" 모달창 닫힘 --%>
					<a href="#" id="changePasswordBtn" class="col-6 border-right">변경하기</a>
					<a href="#" data-dismiss="modal" class="col-6">취소</a>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#changePasswordBtn').on('click', function(e) {
			e.preventDefault();

			// validation
			let currentPassword = $('#currentPassword').val().trim();
			let password = $('#password').val().trim();
			let confirmPassword = $('#confirmPassword').val().trim();
			if (currentPassword == "" || password == "" || confirmPassword == "") {
				alert("전부 입력해주세요.");
				return;
			}

			if (password != confirmPassword) {
				alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
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

			// AJAX - 기존 비밀번호 일치 확인
			$.ajax({
				type : "POST",
				url : "/user/update_password",
				data : {
					"currentPassword" : currentPassword,
					"password" : password
				},
				success : function(data) {
					if (data.result == "success") {
						alert("변경되었습니다. 로그인 화면으로 이동합니다.");
						location.href = "/user/sign_in_view";
					} else {
						alert(data.result);
					}
				},
				error : function(e) {
					alert("비밀번호 변경 중 오류 발생");
				}
			});
		});
	});
</script>