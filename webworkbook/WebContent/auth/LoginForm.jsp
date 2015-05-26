<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
    <h2>사용자 로그인</h2>
    <form action="login.do" method="post">
        이메일: <input type="email" name="email" id="email"><br>
        암호: <input type="password" name="password"><br>
        <input type="submit" value="로그인">
    </form>
    
	<script type="text/javascript">
		window.onload = function() {
		    document.getElementById("email").focus();
		};
	</script>

</body>
</html>