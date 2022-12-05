package com.nagaboka.domain.walk;

import lombok.Data;

@Data
public class WalkReviewAttachFileVO {
	private String fileName;	// 파일 원래 이름
	private String uploadPath;	// 업로드 위치
	private String uuid;		// uuid로 변환된 파일 이름
	private boolean image;		// 이미지 여부 확인
}
