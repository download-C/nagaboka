<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->

<h1>게시판 글쓰기</h1>

<form action="/board/insert" method="post">
	<div class="insertBoard">
		<!-- 아이디, 닉네임 -->
<%-- 		<input type="hidden" name="id" value="${loginID }">  --%>
		<input type="hidden" name="b_nick" value="${sessionScope.b_nick }">
		<!-- 카테고리 -->
		<select name="b_ctgr" class="selectcategory">
			<option value="" disabled selected style="color: black;">카테고리를 선택해주세요</option>
			<option value="일상">일상</option>
			<option value="소식">소식</option>
			<option value="질문">질문</option>
			<option value="상품추천">상품추천</option>
		</select>
	</div>
	<!-- 게시글 제목, 내용 -->
	<input type="text" name="b_title" placeholder="제목을 입력해주세요" required><br>
	<textarea name="b_content"></textarea><br>
	<input type="submit" value="글쓰기" ><br>
</form>

<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>