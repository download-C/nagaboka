<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->


<h1>walk/walkReviewList.jsp</h1>

<div class="container">
	<ul>
	<c:forEach var="list" items="${walkReviewList }">
		<li>
			<!-- 리뷰 작성자 -->
			<div>${list.u_name }</div>
			<!-- 첨부 이미지 -->
			<div>${list.wr_imgs }</div>
			<!-- 리뷰 내용 -->
			<div>${list.wr_con }</div>
			<div>${list.wr_regdate }</div>
		</li>		
	</c:forEach>
	</ul>
</div>


<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>