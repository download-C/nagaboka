<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>

//파일 확장자와 크기 확인
function checkExtension(fileName, fileSize){
	var regEx = new RegExp("(.*?)\.(bmp|jpg|jpeg|png|gif)$");
	var maxSize = 5242880; // 5MB
	if(fileSize>=maxSize){
		alert("파일 사이즈는 5MB를 넘을 수 없습니다.");
		return false;
	} else {
		alert("파일 사이즈 적당~");
	}
	if(!regEx.test(fileName)) {
		alert("이미지 파일만 업로드 가능합니다.");
		return false;
	} else {
		alert("파일 확장자 확인 완료~");
	}
	return true;
}

$(document).ready(function(){
	var formObj = $("form[role='form']");
	
	$("button[type='submit']").on("click", function(e){
		e.preventDefault();
		alert("submit clicked");
	});
	
	// 첨부파일 들어왔을 때 자동으로 확장자 및 파일 사이즈 확인
	$("input[type='file']").change(function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		for(var i=0; i<files.length; i++) {
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			formData.append("uploadFile", files[i]);
		} // for
		
		$.ajax({
			url: '/ajax/reviewUpload',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			dataType: 'json',
			success: function(result) {
				alert("uploaded");
			}
		}); // ajax
		
	}); // input file
}); // document
</script>
<!-- 본문 작성 위치 시작 -->

<h1> walk/writeReview.jsp </h1>
	
	<!-- 산책 장소 후기 남기기  폼 -->
	<form role="form" action="" method="post" enctype="multipart/form-data">
		<!-- 산책 장소 이름 정보  -->
		<input type="hidden" name="wname" value="${wname }">
		<!-- 리뷰 내용 -->
		
		<textarea name="wrcon" placeholder="리뷰 내용"></textarea>
		
		<button type="button" onclick="changeImg()" style="border: none; background-color: transparent;">
		<img id="pawImg" src="${pageContext.request.contextPath}/resources/img/heart-with-dog-paw-48(gray).png">
		</button>
		<input type="hidden" name="wrlike" id="paw" value="false">
		<script>
			function changeImg() {
				var paw = $("#paw");
				if(paw.val()=="false") {
// 					alert("발바닥 누름");
					document.getElementById("pawImg").src="${pageContext.request.contextPath}/resources/img/heart-with-dog-paw-48.png";
					paw.attr('value',"true");
				} else {
// 					alert("발바닥 취소");
					document.getElementById("pawImg").src="${pageContext.request.contextPath}/resources/img/heart-with-dog-paw-48(gray).png";
					paw.attr('value',"false");
				}
			}
		</script>
	</form>
	<hr>
	<div>
		<div><h3>파일 첨부</h3></div>
		<input type="file" name="uploadFile" multiple>
		<div class="uploadResult">
			<ul>
			</ul>
		</div>
	</div>
	<hr>
	<button type="submit">작성</button><button id="reset">초기화</button>
	

<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>