package com.nagaboka.service.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagaboka.domain.board.BoardVO;
import com.nagaboka.persistence.board.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDAO dao;
	
	//1.게시판 글쓰기 insert	
	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		log.info("🎄🎄🎄 1.insertBoard 컨트롤러 호출");
		
	}
	// 1. 글쓰기 insertBoard 끝
	
	
	
}
