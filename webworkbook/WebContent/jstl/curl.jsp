<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="calcUrl" value="http://localhost:9999/calc">
	<c:param name="v1" value="20" />
	<c:param name="v2" value="30" />
	<c:param name="op" value="+" />
</c:url>
<a href="${calcUrl}">계산하기</a> <br>
계산하기 URL: ${calcUrl} <br>
    
    