<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 등록</title>
<style type="text/css">
ul { padding: 0; }
li { list-style: none;}

label {
	float: left;
	text-align: right;
	width: 60px;
}
</style>
</head>
<body>
	<h1>프로젝트 등록</h1>
	
	<form action="add.do" method="post">
	<ul>
		<li>
			<label for="title">제목</label>
			<input type="text" name="title" id="title">
		</li>
		<li>
			<label for="content">내용</label>
			<textarea rows="5" cols="30" name="content" id="content" ></textarea>
		</li>
		<li>
			<label for="startDate">시작일</label>
			<input type="text" name="startDate" id="startDate" placeholder="예)2015-01-01">
		</li>
		 <li>
			<label for="endDate">종료일</label>
			<input type="text" name="endDate" id="endDate" placeholder="예)2015-12-30">
		 </li>
		 <li>
			<label for="tags">태그</label>
			<input type="text" name="tags" id="tags" placeholder="예)태그1 태그2 태그3">
		 </li>
	</ul>
		<input type="submit" value="추가">
		<input type="reset" value="취소">
	</form>
	
	<script type="text/javascript">
		window.onload = function() {
		    document.getElementById("title").focus();
		};
	</script>
</body>
</html>