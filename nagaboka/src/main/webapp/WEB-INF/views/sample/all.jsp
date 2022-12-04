<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/sample/all.jsp</title>
</head>
<body>
	<h1>
		WEB-INF/views/ <span
			style="color: white; background-color: palegreen; font-size: 1.5em">
			🎅🎅all🤶🤶 </span>.jsp
	</h1>
	
<!-- 	<a href="/customLogout">로그아웃</a> -->
	<fieldset style="display: inline;">
		<legend>✨로그아웃👋👋 </legend>
		<form action="/customLogout" method="post">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<input type="submit" value="로 그 아 웃">
		</form>
	</fieldset>

</body>
</html>