<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/customLogin.jsp</title>
</head>
<body>
	<h1>
		WEB-INF/views/ <span
			style="color: white; background-color: orange; font-size: 1.5em">
			🌻🌻 customLogin 🍒🍒 </span>.jsp
	</h1>
	
	<h2><c:out value="${error }" /></h2>
	<h2><c:out value="${logout }" /></h2>

	<fieldset style="display: inline;">
		<legend>✨로그인✨</legend>
		<form action="/login" method="post">
			아디: <input type="text" name="username"> name="id"가 아니라 "username"으로!! <br>
			비번: <input type="password" name="password"> name="pw"가 아니라 "password"!! <br>
			
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" >
			
			<input type="submit" value="로 그 인">
		</form>
	</fieldset>

</body>
</html>