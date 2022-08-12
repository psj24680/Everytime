<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="">
	<div class="board-title">
		<a href="/board/${boardId}/post_list_view">${board.name}</a>
	</div>

	<div class="content-box">
		<div class="write-box">
			<p>
				<input type="text" id="subject" autocomplete="off" placeholder="글 제목">
			</p>
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
			<div>
				<a href="#" class="attach">
					<input type="file" id="file" class="d-none" accept=".jpg,.jpeg,.png,.gif">
					<img alt="attach" src="/static/img/write-attatch-icon.png" id="attach">
				</a>
				<a href="#" class="save">
					<img alt="save" src="/static/img/write-save-icon.png" id="save" data-user-id="${userId}">
				</a>
				<a href="#" class="anonymous">
					<img alt="anonymous" src="/static/img/write-anonymous-icon.png" id="anonymous">
				</a>
			</div>
		</div>

		<div class="post">
			<a href="#">
				<p class="post-title">title</p>
				<p class="post-content">content</p>
				<small class="post-time">time</small>
				<div class="d-flex">
					<p class="post-user">user</p>
					<div>
						<!-- 좋아요, 댓글 수 -->
					</div>
				</div>
			</a>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		// 파일 업로드 버튼 클릭
		$('#attach').on('click', function(e) {
			$('#file').click();
		});

		// 익명 체크 여부
		$('#anonymous').on('click', function() {
			let src = $(this).attr('src');
			if (src.indexOf('active') < 0) {
				$(this).attr('src', '/static/img/write-anonymous-active-icon.png');
			} else {
				$(this).attr('src', '/static/img/write-anonymous-icon.png');
			}
		});
		
		$('#save').on('click', function() {
			// test
			let userId = $(this).data('user-id');
			let subject = $('#subject').val().trim();
			let content = $('#content').val();
			let src = $('#anonymous').attr('src');
			if (src.indexOf('active') < 0) {
				src = "익명 아님";
			} else {
				src = "익명";
			}
			
			alert(userId + "\n" + subject+ "\n" + content + "\n" + src);
			
			// validation
			/* let content = $('#content').val();
			if (content == "") {
				alert("내용을 입력하세요.");
			} */
		});
	});
</script>