<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>

<!-- 본문 작성 위치 시작 -->


<div class="container">

<h1> walk/walkMap.jsp </h1>


<!-- 카카오 지도 시작 -->
<!-- 지도의 LatLng를 처음 페이지 들어올 때 위치 정보 동의 후 해당 정보를 넣어야함. -->
<div id="map" style="width:800px;height:400px;"></div>
	<!-- 카카오 지도 부르는 기본 js -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=21c24f9b63728e6b9c9fb82982497b77"></script>
	<!-- 카카오 지도 추가 라이브러리 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(33.450701, 126.570667),
			level: 7
		};
		var map = new kakao.maps.Map(container, options);
	</script>
<!-- 카카오 지도 끝 -->


</div>
<!-- 본문 작성 위치 끝 -->


<%@ include file="../include/footer.jsp"%>