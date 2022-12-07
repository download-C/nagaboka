package com.nagaboka.domain.walk;

import lombok.Data;

@Data
public class WalkReviewAttachFileVO {
	private String uploadPath;	// 업로드 위치
	private String uuid;		// uuid로 변환된 파일 이름
	private String fileName;	// 파일 원래 이름

	@Override
	public String toString() {
//		String path = "C:\\Users\\USER\\git\\nagaboka\\src\\main\\webapp\\resources\\upload\\";
//		return path+uploadPath+"\\"+uuid+"_"+fileName;
		return uploadPath+"\\"+uuid+"_"+fileName;
	}
	
}
