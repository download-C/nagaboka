package com.nagaboka.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagaboka.service.WalkService;

@RestController
@RequestMapping("/ajax/*")
public class AjaxController {
	
	private static final Logger log = LoggerFactory.getLogger(AjaxController.class);
	
	@Inject
	private WalkService walkService;
	
	// 첨부파일 들어올 경우 업로드한 연/월/일로 폴더 자동 생성
	private String getFolder() {
		// 날짜 포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		// 오늘 날짜를 정해진 포맷 형태로 바꿔서 텍스트로 저장
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
		
	}
	
	@PostMapping(value="/reviewUpload")
	public void reviewUploadPOST(MultipartFile[] uploadFile) throws Exception {
		log.info("reviewUploadPOST(uploadFile) 호출");
		String uploadFolder = "C:\\Users\\USER\\git\\nagaboka\\src\\main\\webapp\\resources\\upload";
		
		// 업로드한 연/월/일로 폴더 생성하기 시작 --------------
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("업로드 위치: "+uploadPath );
		// 해당 폴더가 없을 경우 폴더 생성
		if(uploadPath.exists()==false) {
			uploadPath.mkdir();
			log.info(uploadPath+"패스 생성");
		}
		// 업로드한 연/월/일로 폴더 생성하기 끝 --------------
		
		for(MultipartFile multi: uploadFile) {
			log.info("-----------------------------");
			log.info("업로드 파일명: "+multi.getOriginalFilename());
			log.info("업로드 파일 크기: "+multi.getSize());
			
			String uploadFileName = multi.getOriginalFilename();
			// 앞에서 .까지 잘라서 확장자를 제외한 파일명만 넣기
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("파일 이름: "+uploadFileName);
			
			// 특정 폴더 하나에 저장
//			File saveFile = new File(uploadFolder, uploadFileName);
			// 연/월/일 폴더 만들어서 저장
			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				multi.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
				// TODO: handle exception
			} // catch
		} // for
		
	}
	
	
}
