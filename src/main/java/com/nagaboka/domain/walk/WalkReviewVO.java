package com.nagaboka.domain.walk;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class WalkReviewVO {
	private int wr_no;		// 리뷰번호(자동 생성)
	private WalkVO walk;	// 장소 관련 정보
	private String user_id; // 로그인한 유저 아이디
	private Timestamp wr_regdate; // 작성일 (자동 생성)
	private String wr_con;;	// 리뷰 내용
	private String wr_imgs;	// 리뷰 이미지 정보 -> List로 변경될 듯
	private boolean wr_like;	// 발바닥
	private List<WalkReviewAttachFileVO> attachList;

}
