<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<html>
<head>
<title><decorator:title default="Welcome" /></title>
<decorator:head />
</head>

<body bgcolor=ffffff bottommargin=0 topmargin=0 marginheight=0>
	<!-- TOP Menu -->
	<page:applyDecorator name="header"/>
	<!-- //TOP Menu 영역 -->
	
	<decorator:body />
	
	<!-- footer -->
    <page:applyDecorator name="footer"/>
    <!-- //footer 영역 -->
</body>
</html>
