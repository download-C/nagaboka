package com.nagaboka.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;
import com.nagaboka.service.WalkService;

@Controller
@RequestMapping(value="/walk/*")
public class WalkController {
	
	@Inject
	private WalkService service;
	
	@Inject
	private HttpSession session;

	private static final Logger log = LoggerFactory.getLogger(WalkController.class);
	
	// http://localhost:8088/walk/walkMap
	@GetMapping(value="/walkMap")
	public void walkMapGET() {
		log.info("walk/walkMap 호출");
		// 임시 위도 경도 -> 로그인한 회원의 DB에서 가져올 예정
		double latitude = 35.172661;
		double longitude = 129.108575;
		
		// 회원이 입력한 위도 및 경도를 기반으로 한 산책 장소 목록 가져오기 
		// 축척이 1km일 경우 반경 n 거리 안쪽일 때를 할까 생각 중
		List<WalkVO> walkList = service.getWalkList(latitude, longitude);
	}
	
	// http://localhost:8088/walk/writeReview
	@GetMapping(value="/writeReview")
	public void writeWalkGET() throws Exception{
		log.info("♡♡♡♡♡♡♡♡♡♡walkWriateGET(walk) 호출");
	}
	
	@PostMapping(value="/writeReview") 
	public void writeWalkPOST(WalkVO walk, HttpServletRequest request) {
		log.info("♡♡♡♡♡♡♡♡♡♡walkWriatePOST(walk) 호출");
		String user_id = (String)session.getAttribute("user_id");

		
	}
	
	
}
