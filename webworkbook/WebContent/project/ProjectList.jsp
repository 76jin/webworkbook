<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 목록</title>
</head>
<body>
	<h1>프로젝트 목록</h1>
	<p>
		<a href="add.do">신규 프로젝트</a> <br>
	</p>
	
	<table border="1">
		<tr>
			<th>
				<c:choose>
					<c:when test="${order == 'PNO_ASC'}">
						<a href="list.do?order=PNO_DESC">번호△ </a>
					</c:when>
					<c:when test="${order == 'PNO_DESC'}">
						<a href="list.do?order=PNO_ASC">번호▽</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?order=PNO_ASC">번호</a>
					</c:otherwise>
				</c:choose>
			</th>
			<th>
				<c:choose>
					<c:when test="${order == 'TITLE_ASC'}">
						<a href="list.do?order=TITLE_DESC">제목△ </a>
					</c:when>
					<c:when test="${order == 'TITLE_DESC'}">
						<a href="list.do?order=TITLE_ASC">제목▽</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?order=TITLE_ASC">제목</a>
					</c:otherwise>
				</c:choose>
			</th>
			<th>
				<c:choose>
					<c:when test="${order == 'STARTDATE_ASC'}">
						<a href="list.do?order=STARTDATE_DESC">시작일△ </a>
					</c:when>
					<c:when test="${order == 'STARTDATE_DESC'}">
						<a href="list.do?order=STARTDATE_ASC">시작일▽</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?order=STARTDATE_ASC">시작일</a>
					</c:otherwise>
				</c:choose>
			</th>
			<th>
				<c:choose>
					<c:when test="${order == 'ENDDATE_ASC'}">
						<a href="list.do?order=ENDDATE_DESC">종료일△ </a>
					</c:when>
					<c:when test="${order == 'ENDDATE_DESC'}">
						<a href="list.do?order=ENDDATE_ASC">종료일▽</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?order=ENDDATE_ASC">종료일</a>
					</c:otherwise>
				</c:choose>
			</th>
			<th>
				<c:choose>
					<c:when test="${order == 'STATE_ASC'}">
						<a href="list.do?order=STATE_DESC">상태△ </a>
					</c:when>
					<c:when test="${order == 'STATE_DESC'}">
						<a href="list.do?order=STATE_ASC">상태▽</a>
					</c:when>
					<c:otherwise>
						<a href="list.do?order=STATE_ASC">상태</a>
					</c:otherwise>
				</c:choose>
			</th>
			<th></th>
		</tr>

		<c:forEach var="project" items="${projects}" >
		<tr>
			<td>${project.no}</td>
			<td><a href="update.do?no=${project.no}">${project.title}</a></td>
			<td>${project.startDate}</td>
			<td>${project.endDate}</td>
			<td>${project.state}</td>
			<td><a href="delete.do?no=${project.no}">[삭제]</a></td>
		</tr>
		</c:forEach>		
	</table>
</body>
</html>