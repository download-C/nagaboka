package com.nagaboka.domain.board;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BCommentVO {
	private Integer bc_no; //댓글번호
	private Integer b_no; //게시글번호
	private String u_id; //아이디에서 닉네임으로 추출
	private String bc_content; //댓글내용
	private Timestamp bc_regdate; //댓글등록일
	private Timestamp bc_updatedate; //댓글수정일
	private Integer bc_re_ref;  //댓글그룹
	private Integer bc_re_lev; //댓글들여쓰기
	private Integer bc_re_seq; //댓글순서

}
