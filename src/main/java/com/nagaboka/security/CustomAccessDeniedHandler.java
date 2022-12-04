package com.nagaboka.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.info("(✿◡‿◡) handle  호출됨");
		log.info("(✿◡‿◡) handle  어떤 놈이 권한 없이 접근 중... accessError.jsp 페이지로 이동시킬거");
		
		response.sendRedirect("/accessError");
	}
	
}
