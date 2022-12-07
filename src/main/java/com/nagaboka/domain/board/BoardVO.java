package com.nagaboka.domain.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardVO {
	private Integer b_no; //게시글번호
	private String u_id; //아이디에서 닉네임으로 추출
	private String b_ctgr; //카테고리 전체(일상,소식,질문,상품추천)
	private String b_title; //게시글제목
	private String b_content; //게시글내용
	private Timestamp b_regdate; //게시글등록일자
	private Timestamp b_updatedate; //게시글수정일자
	private Integer b_readcount; //게시글조회수
	private String b_file; //게시글첨부파일
	
}
