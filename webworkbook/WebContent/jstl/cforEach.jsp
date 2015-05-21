<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% pageContext.setAttribute("nameList", new String[] {"홍길동", "임꺽정", "일지매"}); %>
<% pageContext.setAttribute("nameList2", 
		new String[] {"홍길동", "임꺽정", "일지매", "주먹대장", "똘이장군"}); %>

<%
ArrayList<String> nameList3 = new ArrayList<String>();
nameList3.add("홍길동");
nameList3.add("임꺽정");
nameList3.add("일지매");
nameList3.add("주먹대장");
nameList3.add("똘이장군");
pageContext.setAttribute("nameList3", nameList3);
%>

<% pageContext.setAttribute("nameList4", "홍길동,임꺽정,일지매,주먹대장,똘이장군");  %>

<ol>
  <li> 배열 예제
	<ul>
		<c:forEach var="name" items="${nameList}">
			<li>${name}</li>
		</c:forEach>
	</ul>
 </li>
  <li> 배열의 시작 인덱스와 종료 인덱스 지정
  	<ul>
	  	<c:forEach var="name" items="${nameList2}" begin="2" end="3">
  			<li>${name}</li>
  		</c:forEach>
  	</ul>
  </li>
  <li> ArrayList 객체
  	<ul>
  		<c:forEach var="name" items="${nameList3}">
  			<li>${name}</li>
  		</c:forEach>
  	</ul>
  </li>
  <li> 콤마로 구분한 문자열
  	<ul>
  		<c:forEach var="name" items="${nameList4}">
  			<li>${name}</li>
  		</c:forEach>
  	</ul>
  </li>
  <li> 특정 횟수 만큼 반복
  	<ul>
  		<c:forEach var="no" begin="1" end="6">
  			<li><a href="jstl0${no}.jsp">JSTL 예제 ${no}</a></li>
  		</c:forEach>
  	</ul>
  </li>
</ol>
    