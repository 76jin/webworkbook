<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${10 > 20}" var="result1" >
1) 10은 20보다 크다.<br>
</c:if>
2) ${result1}<br>

<c:if test="${10 < 20}" var="result2">
3) 10은 20보다 크다.<br>
</c:if>
4) ${result2}<br>
