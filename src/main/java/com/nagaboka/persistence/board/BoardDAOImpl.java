package com.nagaboka.persistence.board;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagaboka.domain.board.BoardVO;


@Repository
public class BoardDAOImpl implements BoardDAO {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.nagaboka.mapper.BoardMapper";
	
	//1.게시판 글쓰기 insert
	@Override
	public void insertBoard(BoardVO vo) throws Exception {
		log.info("🎄🎄🎄 게시판 글쓰기 insert 호출");
		
		int result = sqlSession.insert(NAMESPACE+".insertBoard", vo);
		
		if (result > 0) {
			log.info("🎄🎄🎄게시판 글쓰기 DB 확인");
		}
		
	}//1.게시판 글쓰기 insert
	
	
} 
