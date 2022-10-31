<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<div class="board-title">
		<a href="/board/${boardId}">${board.name}</a>
	</div>

	<div class="content-box">
		<div class="write-box">
			<c:if test="${boardId eq 1}">
				<p>
					<input type="text" id="subject" autocomplete="off" placeholder="글 제목">
				</p>
			</c:if>
			<p>
				<textarea id="content" class="smallplaceholder" placeholder="에브리타임은 누구나 기분 좋게 참여할 수 있는 커뮤니티를 만들기 위해 커뮤니티 이용규칙을 제정하여 운영하고 있습니다. 위반 시 게시물이 삭제되고 서비스 이용이 일정 기간 제한될 수 있습니다. 

아래는 이 게시판에 해당하는 핵심 내용에 대한 요약 사항이며, 게시물 작성 전 커뮤니티 이용규칙 전문을 반드시 확인하시기 바랍니다. 

※ 정치·사회 관련 행위 금지 
- 국가기관, 정치 관련 단체, 언론, 시민단체에 대한 언급 혹은 이와 관련한 행위 
- 정책·외교 또는 정치·정파에 대한 의견, 주장 및 이념, 가치관을 드러내는 행위 
- 성별, 종교, 인종, 출신, 지역, 직업, 이념 등 사회적 이슈에 대한 언급 혹은 이와 관련한 행위 
- 위와 같은 내용으로 유추될 수 있는 비유, 은어 사용 행위 
* 해당 게시물은 시사·이슈 게시판에만 작성 가능합니다. 

※ 홍보 및 판매 관련 행위 금지 
- 영리 여부와 관계 없이 사업체·기관·단체·개인에게 직간접적으로 영향을 줄 수 있는 게시물 작성 행위 
- 위와 관련된 것으로 의심되거나 예상될 수 있는 바이럴 홍보 및 명칭·단어 언급 행위 
* 해당 게시물은 홍보게시판에만 작성 가능합니다. 

※ 그 밖의 규칙 위반 
- 타인의 권리를 침해하거나 불쾌감을 주는 행위 
- 범죄, 불법 행위 등 법령을 위반하는 행위 
- 욕설, 비하, 차별, 혐오, 자살, 폭력 관련 내용을 포함한 게시물 작성 행위 
- 음란물, 성적 수치심을 유발하는 행위 
- 스포일러, 공포, 속임, 놀라게 하는 행위"></textarea>
			</p>
			<div class="file-name d-none"></div>
			<div>
				<ul class="option">
					<li title="첨부" class="attach"></li>
					<li title="완료" class="save" data-board-id="${boardId}"></li>
					<li title="익명" class="anonymous"></li>
				</ul>
				<input type="file" id="file" class="d-non" accept=".jpg,.jpeg,.png,.gif" multiple="multiple">
			</div>
		</div>

		<div class="post">
			<c:forEach var="post" items="${postList}">
				<a href="/board/${board.id}/post/${post.id}">
					<c:choose>
						<c:when test="${board.id eq 1}">
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
						<c:when test="${board.id ne 1}">
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
</div>

<script>
	$(document).ready(function() {
		// 파일 업로드 버튼 클릭
		$('.attach').on('click', function() {
			$('#file').val('');
			$('.file-name').text('');
			$('.file-name').addClass('d-none');
			$('#file').click();
		});

		// 파일 업로드를 했을 때 확장자 이름 노출, 파일 확장자 검증
		$('#file').on('change', function(e) {
			
			if (e.target.files.length > 1) { // 여러 장 선택 시
				let arr = [];
				for (var i = 0; i < e.target.files.length; i++) {
					// 확장자 검증
					arr.push(e.target.files[i].name.split("."));
					if (arr[i][1] != 'jpg' && arr[i][1] != 'jpeg' && arr[i][1] != 'png' && arr[i][1] != 'gif') {
						alert("이미지 파일만 업로드 할 수 있습니다.");
						
						$(this).val("");
						$('.file-name').text("");
						$('.file-name').addClass('d-none');
						return;
					}
					
					$('.file-name').removeClass('d-none');
					$('.file-name').append(e.target.files[i].name + " / ");
				}
			} else { // 한 장 선택 시
				let arr = e.target.files[0].name.split('.');
				
				if (arr[arr.length - 1] != 'jpg' && arr[arr.length - 1] != 'jpeg' && arr[arr.length - 1] != 'png' && arr[arr.length - 1] != 'gif') {
					alert("이미지 파일만 업로드 할 수 있습니다.");
					
					$(this).val('');
					$('.file-name').text('');
					$('.file-name').addClass('d-none');
					return;
				}
				
				$('.file-name').removeClass('d-none');
				$('.file-name').append(arr[0]);
			}
		});
		
		// 파일 업로드 취소
		$('.file-name').on('click', function() {
			$('#file').val("");
			$('.file-name').text("");
			$('.file-name').addClass('d-none');
		});

		// 익명 체크 여부
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
			let boardId = $(this).data('board-id');

			let subject = null;
			if ($('#subject').val() != null) {
				subject = $('#subject').val().trim();
			}
			if (boardId == 1) {
				if (subject == "") {
					alert("제목을 입력하세요.");
					return;
				}
			}

			let content = $('#content').val();
			if (content == "") {
				alert("내용을 입력하세요.");
				return;
			}

			let anonymous = null;
			if ($('.anonymous').hasClass('active')) {
				anonymous = "O";
			} else {
				anonymous = "X";
			}

			// form태그 생성
			let formData = new FormData();
			formData.append("boardId", boardId);
			formData.append("subject", subject);
			formData.append("content", content);
			formData.append("anonymous", anonymous);
			formData.append("file", $('#file').files);

			alert('file:' + $('#file').files);
			
			// AJAX - DB insert
			/* $.ajax({
				type : "POST",
				url : "/post/create",
				data : formData,
				encType : "multipart/form-data",
				processData : false,
				contentType : false,
				success : function(data) {
					if (data.result == "success") {
						location.reload(true);
					} else {
						alert(data.result);
					}
				},
				error : function(e) {
					alert("글 저장 중 오류 발생");
				}
			}); */
		});
	});
</script>