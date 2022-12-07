<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ${pageContext.request.contextPath} -->
<%@ include file="../include/header.jsp"%>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<!-- 본문 작성 위치 시작 -->
<style>
	html, body {
		width:100%;
		height:100%;
		margin:0;
		padding:0;
	} 
	.map_wrap {
		position:relative;
		overflow:hidden;
		width:100%;
		height:350px;
	}
	.radius_border{
		border:1px solid #919191;
		border-radius:5px;
	}     
	.custom_typecontrol {
		position:absolute;
		top:10px;
		right:10px;
		overflow:hidden;
		width:130px;
		height:30px;
		margin:0;
		padding:0;
		z-index:1;
		font-size:12px;
		font-family:'Malgun Gothic', '맑은 고딕', sans-serif;
	}
	.custom_typecontrol span {
		display:block;
		width:65px;
		height:30px;
		float:left;
		text-align:center;
		line-height:30px;
		cursor:pointer;
	}
	.custom_typecontrol .btn {
		background:#fff;
		background:linear-gradient(#fff,  #e6e6e6);
	}       
	.custom_typecontrol .btn:hover {
		background:#f5f5f5;
		background:linear-gradient(#f5f5f5,#e3e3e3);
	}
	.custom_typecontrol .btn:active {
		background:#e6e6e6;
		background:linear-gradient(#e6e6e6, #fff);
	}    
	.custom_typecontrol .selected_btn {
		color:#fff;
		background:#425470;
		background:linear-gradient(#425470, #5b6d8a);
	}
	.custom_typecontrol .selected_btn:hover {
		color:#fff;
	}   
	.custom_zoomcontrol {
		position:absolute;
		top:50px;
		right:10px;
		width:36px;
		height:80px;
		overflow:hidden;
		z-index:1;
		background-color:#f5f5f5;
	} 
	.custom_zoomcontrol span {
		display:block;
		width:36px;
		height:40px;
		text-align:center;
		cursor:pointer;
	}     
	.custom_zoomcontrol span img {
		width:15px;
		height:15px;
		padding:12px 0;
		border:none;
	}             
	.custom_zoomcontrol span:first-child{
		border-bottom:1px solid #bfbfbf;
	}            
</style>

<div class="container">

<h1> walk/walkMap.jsp </h1>

<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div> 
    <!-- 지도타입 컨트롤 div 입니다 -->
    <div class="custom_typecontrol radius_border">
        <span id="btnRoadmap" class="selected_btn" onclick="setMapType('roadmap')">지도</span>
        <span id="btnSkyview" class="btn" onclick="setMapType('skyview')">스카이뷰</span>
    </div>
    <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
    <div class="custom_zoomcontrol radius_border"> 
        <span onclick="zoomIn()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png" alt="확대"></span>  
        <span onclick="zoomOut()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png" alt="축소"></span>
    </div>
    
    
</div>
    
<button type="button" onclick="getCurrentPosBtn()">내 위치 가져오기</button>

<button id = "find-me">Show my location</button><br/>
<p id = "status"></p>
<a id = "map-link" target="_blank"></a>


<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=138c04b3047a5bf2e01469fef0a92ff1"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<script>
// 	$(document).ready(function() {
	alert("test");


		//===================== geolocation api 시작 =====================
		
		navigator.geolocation.getCurrentPosition(onGeoOkay, onGeoError);
		
		function onGeoOkay(position) {
			
			// 위도 경도 변수 선언
			const w_lat = position.coords.latitude;
			const w_lng = position.coords.longitude;
			console.log("You live in ", w_lat, w_lng);  
			
			//=====================카카오 지도 api 시작=====================
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new kakao.maps.LatLng(w_lat, w_lng), // 지도의 중심좌표
			        level: 6 // 지도의 확대 레벨
			    };
			  
			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
			var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		
		
		
//	 	//현재 위치 가져오기
//	 	function locationLoadSuccess(pos){
//	 	    // 현재 위치 받아오기
//	 	    var currentPos = new kakao.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
		
//	 	    // 지도 이동(기존 위치와 가깝다면 부드럽게 이동)
//	 	    map.panTo(currentPos);
		
//	 	    // 마커 생성
//	 	    var marker = new kakao.maps.Marker({
//	 	        position: currentPos
//	 	    });
		
//	 	    // 기존에 마커가 있다면 제거
//	 	    marker.setMap(null);
//	 	    marker.setMap(map);
//	 	};
		
//	 	function locationLoadError(pos){
//	 	    alert('위치 정보를 가져오는데 실패했습니다.');
//	 	};
		
//	 	// 위치 가져오기 버튼 클릭시
//	 	function getCurrentPosBtn(){
//	 	    navigator.geolocation.getCurrentPosition(locationLoadSuccess,locationLoadError);
		    
		//=====================카카오 지도 api 종료=====================
		
		
		}

	function onGeoError() {
	  alert("위치 정보를 찾아올 수 없습니다.");
	}
	
	//===================== geolocation api 종료 =====================

	
	
// });
	
	//지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
	function setMapType(maptype) { 
	    var roadmapControl = document.getElementById('btnRoadmap');
	    var skyviewControl = document.getElementById('btnSkyview'); 
	    if (maptype === 'roadmap') {
	        map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);    
	        roadmapControl.className = 'selected_btn';
	        skyviewControl.className = 'btn';
	    } else {
	        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);    
	        skyviewControl.className = 'selected_btn';
	        roadmapControl.className = 'btn';
	    }
	}
	
	// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
	function zoomIn() {
	    map.setLevel(map.getLevel() - 1);
	}
	
	// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
	function zoomOut() {
	    map.setLevel(map.getLevel() + 1);
	}
</script>
<!-- 카카오 지도 끝 -->


</div>
<!-- 본문 작성 위치 끝 -->


<%@ include file="../include/footer.jsp"%>