<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%!
public static class MyMember {
	int no;
	String name;
	
	public int getNo() { return no; }
	public void setNo(int no) { this.no = no; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
}
%>
<%
MyMember member = new MyMember();
member.setNo(100);
member.setName("홍길동");
pageContext.setAttribute("member", member);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>c:set property</title>
</head>
<body>
	<h3>객체의 프로퍼티 값 변경</h3>
	9)  ${member.name} <br>
	<c:set target="${member}" property="name" value="임꺽정" />
	10) ${member.name} <br>
</body>
</html>