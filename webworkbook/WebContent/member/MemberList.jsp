<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
    <h1>회원 목록</h1>
    <p><a href='add.do'>신규 회원</a></p>
    
    <table border="1">
    	<tr>
    		<th>
    			<c:choose>
    				<c:when test="${order == 'MNO_ASC'}">
    					<a href="list.do?order=MNO_DESC">회원번호△</a>
    				</c:when>
    				<c:when test="${order == 'MNO_DESC'}">
    					<a href="list.do?order=MNO_ASC">회원번호▽</a>
    				</c:when>
    				<c:otherwise>
    					<a href="list.do?order=MNO_ASC">회원번호</a>
    				</c:otherwise>
    			</c:choose>
    		</th>
    		<th>
    			<c:choose>
    				<c:when test="${order == 'MNAME_ASC'}">
    					<a href="list.do?order=MNAME_DESC">이름△</a>
    				</c:when>
    				<c:when test="${order == 'MNAME_DESC'}">
    					<a href="list.do?order=MNAME_ASC">이름▽</a>
    				</c:when>
    				<c:otherwise>
    					<a href="list.do?order=MNAME_ASC">이름</a>
    				</c:otherwise>
    			</c:choose>
    		</th>
    		<th>
    			<c:choose>
    				<c:when test="${order == 'EMAIL_ASC'}">
    					<a href="list.do?order=EMAIL_DESC">이메일△</a>
    				</c:when>
    				<c:when test="${order == 'EMAIL_DESC'}">
    					<a href="list.do?order=EMAIL_ASC">이메일▽</a>
    				</c:when>
    				<c:otherwise>
    					<a href="list.do?order=EMAIL_ASC">이메일</a>
    				</c:otherwise>
    			</c:choose>
    		</th>
    		<th>
    			<c:choose>
    				<c:when test="${order == 'CRE_DATE_ASC'}">
    					<a href="list.do?order=CRE_DATE_DESC">등록일△</a>
    				</c:when>
    				<c:when test="${order == 'CRE_DATE_DESC'}">
    					<a href="list.do?order=CRE_DATE_ASC">등록일▽</a>
    				</c:when>
    				<c:otherwise>
    					<a href="list.do?order=CRE_DATE_ASC">등록일</a>
    				</c:otherwise>
    			</c:choose>
    		</th>
    		<th></th>
    	</tr>
    	
	    <c:forEach var="member" items="${members}">
	    <tr>
	        <td>${member.no}</td>
	        <td><a href='update.do?no=${member.no}'>${member.name}</a></td>
	        <td>${member.email}</td>
	        <td>${member.createdDate}</td>
	        <td><a href='delete.do?no=${member.no}'>[삭제]</a></td>
	    </tr>
	    </c:forEach>
    </table>
    
</body>
</html>