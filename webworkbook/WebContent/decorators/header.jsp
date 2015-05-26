<%@page import="spms.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!--head start-->
<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
	<a style="color:white;" href="<%=request.getContextPath()%>">
		SPMS (Simple Project Management System)
	</a>
<span style="float: right;">

<c:choose>
 <c:when test="${not empty sessionScope.member && not empty sessionScope.member.email}">
 	${member.email}
	<a style="color:white;"
	    href="<%=request.getContextPath()%>/auth/logout.do">로그아웃</a>
 </c:when>
 <c:otherwise>
	<a style="color:white;"
	    href="<%=request.getContextPath()%>/auth/login.do">로그인</a>
	<a style="color:red;"
	    href="<%=request.getContextPath()%>/auth/join.do">회원가입</a>
 </c:otherwise>
</c:choose>


</span>

</div>
<!--head end-->
