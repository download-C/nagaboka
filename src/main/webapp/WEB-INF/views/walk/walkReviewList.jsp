<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->


<h1>walk/walkReviewList.jsp</h1>

<div class="container">
	<!-- 리뷰에 작성한 첨부파일 모두 보여주는 곳 -->
	<div style="border: 2px solid lime">
		<h3>썸네일 모아보기 위치</h3>
		<div>
		<c:forEach var="thumbnail" items="${map.thumbnailList }" end="4">
			<div>
				<img src="${pageContext.request.contextPath}/resources/upload/review/${thumbnail }">
			</div>
		</c:forEach>
		<button onclick="" style="width: 100%">사진 더 보기 </button>
		</div>
	</div>
	
	<div>
	<c:forEach var="list" items="${walkReviewList }">
		<div style="border: 2px solid black; margin: 2px;">
			<!-- 리뷰 작성자 -->
			<div style="border: 2px solid gray; margin: 2px;">
				<b>${list.u_name }</b>
				
			</div>
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
			<!-- 리뷰 작성 시간 -->
			<div style="border: 2px solid blue; margin: 2px;"><fmt:formatDate value="${list.wr_regdate }" pattern="yyyy.MM.dd HH:mm" /> </div>
			<!-- 수정 삭제 버튼 -->
			<div>
<%-- 			<c:if test="${list.u_name eq sessionScope.u_name} || ${list.u_name} eq '관리자'">ㅊ --%>
				<button>수정</button>
				<button>삭제</button>
<%-- 			</c:if> --%>
			</div>
		</div>		
			<br>
	</c:forEach>
	</div>
</div>
<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>