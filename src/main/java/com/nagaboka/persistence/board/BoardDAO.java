package com.nagaboka.persistence.board;

import com.nagaboka.domain.board.BoardVO;

public interface BoardDAO {
	//커뮤니티 게시판 board
	
	//1.게시판 글쓰기 insert
	public void insertBoard(BoardVO vo) throws Exception;

	
}
