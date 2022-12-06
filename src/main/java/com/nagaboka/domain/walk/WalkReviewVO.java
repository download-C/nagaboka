package com.nagaboka.domain.walk;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class WalkReviewVO {
	private int wrno;		// 리뷰번호(DB에서 자동 생성)
	private WalkVO walk;	// 장소 관련 정보
	private String u_id; // 로그인한 유저 아이디 (세션값)
	private Timestamp wrregdate; // 작성일 (DB에서 자동 생성)
	private String wrcon;;	// 리뷰 내용
	private String wrimgs;	// 리뷰 이미지 정보 -> List로 변경될 듯
	private boolean wrlike;	// 발바닥
	private List<WalkReviewAttachFileVO> attachList;

}
