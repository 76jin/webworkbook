<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% pageContext.setAttribute("tokens", "v1=20&v2=30&op=+"); %>
<ul>
	<c:forTokens var="item" items="${tokens}" delims="&">
		<li>${item}</li>
	</c:forTokens>
</ul>


    