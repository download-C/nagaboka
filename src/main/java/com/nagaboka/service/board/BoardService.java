package com.nagaboka.service.board;

import java.util.List;

import com.nagaboka.domain.board.BoardVO;


public interface BoardService {
	
	//1.게시판 글쓰기 insert
	public void insertBoard(BoardVO vo) throws Exception;
	

}
