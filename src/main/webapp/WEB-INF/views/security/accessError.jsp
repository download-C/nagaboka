<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- $${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->
${cursor}

	<h1>
		WEB-INF/views/ <span
			style="color: white; background-color: red; font-size: 1.5em">
			💩💩 accessError 😰😰 </span>.jsp
	</h1>
	<h3>🙀🙀 접근 권한이 없습니다,,,,,🙀🙀</h3>
	<h3>🙀 관리자에게 문의해주세요🙀</h3>
	
	CommonController에서 보낸 msg: <h3> ${msg }</h3> <hr>
	인증 정보: <h4> ${auth }</h4> <hr>
	SPRING_SECURITY_403_EXCEPTION.getMessage(): <h4> ${SPRING_SECURITY_403_EXCEPTION.getMessage() }</h4>
	
<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>