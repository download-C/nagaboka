package com.nagaboka.domain.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BoardLikeVO {
	private Integer bl_no; //좋아요번호
	private Integer b_no; //게시글번호
	private String u_id; //아이디에서 닉네임으로 추출
	private Integer b_like; //게시글좋아요
	
}
