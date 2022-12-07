<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->


<h1>walk/walkReviewList.jsp</h1>

<div class="container">
	<ul>
	<c:forEach var="list" items="${walkReviewList }">
		<div style="border: 2px solid black; margin: 2px;">
			<!-- 리뷰 작성자 -->
			<div style="border: 2px solid gray; margin: 2px;"><b>${list.u_name }</b></div>
			<!-- 첨부 이미지 쪼개서 들고오기 -->
			<div style="border: 2px solid yellow; margin: 2px;">
			<c:forEach var="attach" items="${list.attachList }" varStatus="status">
				<c:forEach var="thumbnail" items="${attach.thumbnail }">
				<img src="${pageContext.request.contextPath}/resources/upload/review/${thumbnail}">
				</c:forEach>
			</c:forEach>
			</div>
			<!-- 리뷰 내용 -->
			<div style="border: 2px solid red; margin: 2px;">${list.wr_con }</div>
			<div style="border: 2px solid blue; margin: 2px;"><fmt:formatDate value="${list.wr_regdate }" pattern="yyyy.MM.dd HH:mm" /> </div>
		</div>		
			<br>
	</c:forEach>
	</ul>
</div>
<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>