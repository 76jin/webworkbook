<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 정보</title>
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
	<h1>프로젝트 정보</h1>
	
	<form action="update.do" method="post">
	<ul>
		<li>
			<label for="no">번호</label>
			<input type="text" name="no" id="no" value="${project.no}" readonly>
		</li>
		<li>
			<label for="title">제목</label>
			<input type="text" name="title" id="title" value="${project.title}">
		</li>
		<li>
			<label for="content">내용</label>
			<textarea rows="5" cols="30" name="content" id="content">${project.content}</textarea>
		</li>
		<li>
			<label for="startDate">시작일</label>
			<input type="text" name="startDate" id="startDate"
				value="${project.startDate}" placeholder="예)2015-01-01">
		</li>
		 <li>
			<label for="endDate">종료일</label>
			<input type="text" name="endDate" id="endDate"
				value="${project.endDate}" placeholder="예)2015-12-30">
		 </li>
		 <li>
			<label for="state">상태</label>
			 <select id="state" name="state">
				  <option value="0" ${project.state == 0 ? "selected" : ""}>준비</option>
				  <option value="1" ${project.state == 1 ? "selected" : "" }>진행</option>
				  <option value="2" ${project.state == 2 ? "selected" : "" }>완료</option>
				  <option value="3" ${project.state == 3 ? "selected" : "" }>취소</option>
			</select> 
		 </li>
		 <li>
			<label for="tags">태그</label>
			<input type="text" name="tags" id="tags"
				value="${project.tags}" placeholder="예)태그1 태그2 태그3">
		 </li>
	</ul>
		<input type="submit" value="추가">
		<input type="button" value="삭제"
				onclick='location.href="delete.do?no=${project.no}"'>
		<input type="reset" value="취소" onclick='location.href="list.do"'><br>
	</form>
	
	<script type="text/javascript">
		window.onload = function() {
		    document.getElementById("title").focus();
		};
	</script>
</body>
</html>