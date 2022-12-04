package com.nagaboka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/walk/*")
public class WalkController {

	private static final Logger log = LoggerFactory.getLogger(WalkController.class);
	
	// http://localhost:8088/walk/walkMap
	@GetMapping(value="/walkMap")
	public void walkMapGET() {
		log.info("walk/walkMap 호출");
		
	}
}
