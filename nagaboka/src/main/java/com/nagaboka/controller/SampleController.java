package com.nagaboka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/sample/*")
public class SampleController {
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	
	
	// CASE 1. /sample/all -> 로그인 X 
	@GetMapping(value = "/all")
	public void allGet() {
		log.info("φ(゜▽゜*)♪ 1.allGet()   호출됨");
	}
	// CASE 1. /sample/all -> 로그인 X  끝
	
	
	
	@GetMapping(value = "/member")
	public void memberGet() {
		log.info("φ(゜▽゜*)♪ 2.memberGet()   호출됨");
	}
	// CASE 2. /sample/member -> 로그인 O 끝

	
	
	// CASE 3. /sample/admin -> 로그인 O + 관리자 
	@GetMapping(value = "/admin")
	public void adminGet() {
		log.info("φ(゜▽゜*)♪ 3.adminGet()   호출됨");
	}
	// CASE 3. /sample/admin -> 로그인 O + 관리자 끝	
	
	
	
} // class SampleController
