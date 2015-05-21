<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:parseDate var="date1" value="2015-05-20" pattern="yyyy-MM-dd" />
<fmt:formatDate value="${date1}" pattern="MM/dd/yy"/>

