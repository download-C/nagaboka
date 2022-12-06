<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->


<h1>walk/walkReviewList.jsp</h1>

<div class="container">
	<table class="table table-hover" style="box-shadow: 7px 14px 90px 3px rgba(163, 174, 184, 0.7);">
		<thead>
			<tr style="text-align: center; color:white; background-color: #5107B0;" >
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${walkReviewList }">
			<tr style="text-align: center;">
				<td><b>관리자</b></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>