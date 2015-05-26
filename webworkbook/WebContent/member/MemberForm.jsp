<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등록</title>
</head>
<body>
	<h1>회원 등록</h1>
	<form action='add.do' method='post'>
		이름: <input type='text' name='name' id="name"><br>
		이메일: <input type='email' name='email'><br>
		암호: <input type='password' name='password'><br>
		<input type='submit' value='추가'>
		<input type='reset' value='취소'><br>
	</form>
	
	<script type="text/javascript">
		window.onload = function() {
		    document.getElementById("name").focus();
		};
	</script>
</body>
</html>