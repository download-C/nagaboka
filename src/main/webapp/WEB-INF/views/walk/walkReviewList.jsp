<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->


<h1>walk/walkReviewList.jsp</h1>

<div class="container">
	<ul>
	<c:forEach var="list" items="${walkReviewList }">
		<div class="">
			<!-- 리뷰 작성자 -->
			<div><b>${list.u_name }</b></div>
			<!-- 첨부 이미지 쪼개서 들고오기 -->
			<c:forEach var="attach" items="${list.attachList }" varStatus="status">
				<c:forEach var="thumbnail" items="${attach.thumbnail }">
				<img src="${pageContext.request.contextPath}/resources/upload/review/${thumbnail}">
				</c:forEach>
			</c:forEach>
			<!-- 리뷰 내용 -->
			<div>${list.wr_con }</div>
			<div><fmt:formatDate value="${list.wr_regdate }" pattern="yyyy.MM.dd HH:mm" /> </div>
			<br>
		</div>		
	</c:forEach>
	</ul>
</div>

위치: 
<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>