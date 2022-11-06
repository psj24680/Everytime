<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에브리타임</title>
<!-- JQUERY -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<!-- BOOTSTRAP -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<!-- STYLESHEET -->
<link rel="stylesheet" href="/static/css/styles.css" type="text/css">
<link rel="stylesheet" href="/static/css/gnb_styles.css" type="text/css">
<link rel="stylesheet" href="/static/css/main_styles.css" type="text/css">
<link rel="stylesheet" href="/static/css/board_styles.css" type="text/css">
<link rel="stylesheet" href="/static/css/post_styles.css" type="text/css">

</head>
<body>
	<div id="wrap">
		<header>
			<jsp:include page="../include/gnb.jsp" />
		</header>
		<section>
			<jsp:include page="../${viewName}.jsp"></jsp:include>
		</section>
		<footer>
			<jsp:include page="../include/footer.jsp"></jsp:include>
		</footer>
	</div>
</body>
</html>