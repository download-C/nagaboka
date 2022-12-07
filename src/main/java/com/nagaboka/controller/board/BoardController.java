package com.nagaboka.controller.board;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagaboka.domain.board.BoardVO;
import com.nagaboka.service.board.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@Inject
	private HttpSession session; 
	
	//커뮤니티 게시판 board
	
	//1.게시판 글쓰기 insert - GET
	// http://localhost:8088/board/insert
	@RequestMapping (value = "/insert", method = RequestMethod.GET)
	public String insertBoardGET(RedirectAttributes rttr) throws Exception {
		log.info("🎄🎄🎄 insertBoardGET 호출");
		log.info("🎄🎄🎄 insertBoardGET - /board/insert.jsp로 이동");
		
		//로그인 세션 자리
		
		return "/board/insert";
	}
	
	//1.게시판 글쓰기 insert - POST
	@RequestMapping (value="/insert", method = RequestMethod.POST)
	public String insertBoardPOST(BoardVO vo, RedirectAttributes rttr) throws Exception {
		log.info("🎄🎄🎄 insertBoardPOST 호출");
		
		//전달 정보 저장
		log.info("🎄🎄🎄 insertBoardPOST - BoardVO: " +vo);
		
		log.info("🎄🎄🎄 insertBoardPOST - Service 호출");
		service.insertBoard(vo);
		
		//확인용
		rttr.addFlashAttribute("msg", "msg확인"); 
		
		log.info("🎄🎄🎄 registerPOST - redirect:/board/list로 이동");
		
		//list로 페이지 이동
		return "/board/list"; 
	}
	
	
	
	
	
}
