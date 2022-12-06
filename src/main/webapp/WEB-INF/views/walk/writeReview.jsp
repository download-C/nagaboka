<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<style>
.uploadResult {
	width: 100%;
	background-color: yellow;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}

.uploadResult ul li span {
	color: white;
}

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top:0%;
	width: 100%;
	height: 100%;
 	background-color: rgba(0,0,0,0.6); 
/* 	background-color: gray; */
	z-index: 100;
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	width: 80%;
}
</style>

<!-- 파일 확장자와 크기 확인 함수 -->
<script>
function checkExtension(fileName, fileSize){
	var regEx = new RegExp("(.*?)\.(bmp|jpg|jpeg|png|gif)$");
	var maxSize = 5242880; // 5MB
	if(fileSize>=maxSize){
		alert("파일 사이즈는 5MB를 넘을 수 없습니다.");
		return false;
	} else {
// 		alert("파일 사이즈 적당~");
	}
	if(!regEx.test(fileName)) {
		alert("이미지 파일만 업로드 가능합니다.");
		return false;
	} else {
// 		alert("파일 확장자 확인 완료~");
	}
	return true;
}
</script>

<!-- 발바닥 누르는 펑션 -->
<script>
function changeImg() {
	var paw = $("#paw");
	if(paw.val()==0) {
// 		alert("발바닥 누름");
		document.getElementById("pawImg").src="${pageContext.request.contextPath}/resources/img/heart-with-dog-paw-48.png";
		paw.attr('value',1);
	} else {
// 		alert("발바닥 취소");
		document.getElementById("pawImg").src="${pageContext.request.contextPath}/resources/img/heart-with-dog-paw-48(gray).png";
		paw.attr('value',0);
	}
}
</script>

<!-- 이미지 업로드 후 제목 띄우기 -->
<script>
function showUploadedFile(uploadResultArr) {
	var str = "";

	$(uploadResultArr).each(function(i, obj){
		// 첨부파일의 섬네일용 경로 및 파일명 불러와서 띄우기
		var fileCallPath = encodeURIComponent(obj.uploadPath+"/s_"+obj.uuid+"_"+obj.fileName);
		// 원래 파일 경로 불러와서 자르기
		var originPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName; 
		originPath = originPath.replace(new RegExp(/\\/g),"/");
		str += "<li><div>";
		str += "<a href=\"javascript:showImage(\'"+originPath+"\')\"><img src='/ajax/display?fileName="+fileCallPath+"'></a>";
		str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image'>X</button>";
		str += "</div></li>";
	});

	$(".uploadResult ul").append(str);

}
</script>

<!-- 썸네일 클릭 시 원본 파일 보여주기 -->
<script>
function showImage(fileCallPath) {
// 	alert(fileCallPath);
	$(".bigPictureWrapper").css("display", "flex").show();
	$(".bigPicture").html("<img src='/ajax/display?fileName="
			+encodeURI(fileCallPath)+"'>")
			.animate({width: '100%', height: '100%'}, 1000);
}
</script>

<!-- document -->
<script>
$(document).ready(function(){
	
	// 버튼 클릭 시 서브밋
	var formObj = $("form[role='form']");
	
	$("button[type='submit']").on("click", function(e){
		e.preventDefault();
		alert("작성 버튼 클릭");
	});
	
// <input type='file'> 초기화하기 ==============================
	var cloneObj = $(".uploadDiv").clone();
	
// 첨부파일 들어왔을 때 자동으로 확장자 및 파일 사이즈 확인 후 업로드 ===============
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
// 				alert("업로드 성공");
				// 업로드 성공한 파일 정보들 jsonList 형태로 콘솔에 찍기
				console.log(result);
				
				// 업로드 성공한 파일명 띄워주기
				showUploadedFile(result);
// 				alert("파일명 띄우기 성공");
				
				// 업로드 성공 후 첨부파일 부분 초기화
				$(".uploadDiv").html(cloneObj.html());
// 				alert("초기화 성공");
				// 업로드된 이미지 썸네일 띄우기
				// 1. 파일 이름 띄우기
			}
		}); // ajax
	}); // input file
	
// 원본파일 클릭 시 사라지게 하기 =================================
	$(".bigPictureWrapper").on("click", function(e){
		$(".bigPicture").animate({width:'0%',height:'0%'}, 1000);
		setTimeout(()=> {
			$(this).hide();
		}, 1000);
	});// bigPictureWrapper
	
	$(".uploadResult").on("click","button", function(e){
		
		// x 버튼을 누른 파일과 파일 확장자 확인
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log("파일명: "+targetFile+", 타입: "+type);
		
		// x 버튼을 누른 객체를 감싸고 있는 li 찾기
		var targetLi = $(this).closest("li");
		
		// 비동기로 x를 누른 파일의 정보를 전송
		$.ajax({
			url: '/ajax/deleteFile',
			data: {fileName: targetFile, type:type},
			dataType: 'text',
			type: 'POST',
			success: function(result) {
				alert(result);
				targetLi.remove();
			}
		}); // ajax
	}); // uploadDelete
	
}); // document
</script>
<!-- 본문 작성 위치 시작 -->

<h1> walk/writeReview.jsp </h1>
	
	<!-- 산책 장소 후기 남기기  폼 -->
	<form role="form" action="" onsubmit="return false" enctype="multipart/form-data">
		<!-- 산책 장소 이름 정보  -->
		<input type="hidden" name="wname" value="${wname }">
		
		<!-- 리뷰 내용 -->
		<textarea name="wrcon" placeholder="리뷰 내용"></textarea>

		<!-- 발바닥 모양 버튼 -->
		<button type="button" onclick="changeImg()" style="border: none; background-color: transparent;">
			<img id="pawImg" src="${pageContext.request.contextPath}/resources/img/heart-with-dog-paw-48(gray).png">
		</button>
		<input type="hidden" name="wrlike" id="paw" value=0>
	</form>
	<hr>
	<div class="uploadDiv">
		<div><h3>파일 첨부</h3></div>
		<input type="file" name="uploadFile" multiple> <br>
	</div>
	<div class="uploadResult">
		<ul>
		 
		</ul>
	</div>
	<!-- 썸네일 클릭 시 원본 파일 보여주는 영역 -->
	<div class="bigPictureWrapper">
		<div class="bigPicture">
		</div>
	</div>

	<hr>
	<button type="submit">작성</button><button id="reset">초기화</button>
	

<!-- 본문 작성 위치 끝 -->

<%@ include file="../include/footer.jsp"%>