package com.nagaboka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	private static final Logger log = LoggerFactory.getLogger(CommonController.class);
	
	
	@GetMapping("/accessError")
	public void accessDenied(Model model, Authentication auth) {
		log.info("(●ˇ∀ˇ●) accessDenied  호출됨");
		log.info("(●ˇ∀ˇ●) accessDenied  접근 권한 없음~~~");
		
		model.addAttribute("msg", "접근 권한 없는 사용자 들어옴ㄷㄷ");
		model.addAttribute("auth", auth); // 인증 정보도 뷰로 출력해보기
	}

	
	@GetMapping("/customLogin")
	public void myLoginGET(String error, String logout, Model model) {
		log.info("(●ˇ∀ˇ●) myLoginGET  호출됨");
		log.info("(●ˇ∀ˇ●) myLoginGET  error: " + error);
		log.info("(●ˇ∀ˇ●) myLoginGET  logout: " + logout);
		
		if (error != null) { model.addAttribute("error", "로그인 에러ㄱ- 계정을 다시 확인해주세요"); }
		if (logout != null) { model.addAttribute("logout", "로그아웃 성공"); }
	}
	
	
} // class SampleController
