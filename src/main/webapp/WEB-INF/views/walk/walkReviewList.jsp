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
				<th>리뷰내용</th>
				<th>발바닥</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${walkReviewList }">
			<tr style="text-align: center;">
				<td>${list.u_name }</td>
				<td>${list.wr_con }</td>
				<td>${list.wr_like }</td>
				<td><fmt:formatDate value="${list.wr_regdate }" pattern="yyyy.MM.dd hh:mm" /> </td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>