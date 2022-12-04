<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- $${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->
${cursor}

	<h1>
		WEB-INF/views/ <span
			style="color: white; background-color: pink; font-size: 1.5em">
			😎😎member🎄🎄 </span>.jsp
	</h1>
	
<!-- 	<a href="/customLogout">로그아웃</a> -->
	<fieldset style="display: inline;">
		<legend>✨로그아웃👋👋 </legend>
		<form action="/customLogout" method="post">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<input type="submit" value="로 그 아 웃">
		</form>
	</fieldset>

<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>