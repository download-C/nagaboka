package com.nagaboka.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	private static final Logger log = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("(o゜▽゜)o☆  onAuthenticationSuccess  로그인 성공!!!");
		
		// STEP 1. 로그인 성공한 유저 권한 정보 체크
		
		// 권한 정보를 저장할 리스트 객체 생성
		List<String> roleNames = new ArrayList<String>();
		
		// 로그인 성공한 사용자에 부여되는 권한 객체 authentication
		authentication.getAuthorities().forEach(new Consumer<GrantedAuthority>() {
			@Override
			public void accept(GrantedAuthority authority) {roleNames.add(authority.getAuthority());}
		}); // forEach
		
		log.info("(o゜▽゜)o☆  onAuthenticationSuccess  roleNames: " + roleNames);
		
		
		// STEP 2. 권한에 따른 페이지 이동
		// 관리자 로그인 성공 시, 바로 admin 페이지로 보내버림..
		//   추후 필요한 페이지로 이동하도록 수정하기!!
		if(roleNames.contains("ROLE_ADMIN")) {
			log.info("(o゜▽゜)o☆  onAuthenticationSuccess  관리자님 로그인 성공");
			response.sendRedirect("/sample/admin");
			return;
		}
		
		// 멤버 로그인 성공 시, 바로 member 페이지로 보내버림
		if(roleNames.contains("ROLE_MEMBER")) {
			log.info("(o゜▽゜)o☆  onAuthenticationSuccess  멤버님 로그인 성공");
			response.sendRedirect("/sample/member");
			return;
		}
		
		// 그 외 = 비회원
		log.info("(o゜▽゜)o☆  onAuthenticationSuccess  비회원은.. 메인으로 보내기");
		response.sendRedirect("/");
	}
	
	
}
