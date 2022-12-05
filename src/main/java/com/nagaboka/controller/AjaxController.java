package com.nagaboka.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagaboka.domain.walk.WalkReviewAttachFileVO;
import com.nagaboka.service.WalkService;

import net.coobird.thumbnailator.Thumbnailator;

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
	
	// 파일의 확장자로 첨부파일이 그림인지 확인 후 image가 아닐 경우에는 false 반환
	private boolean checkImageType(File file) {
		try {
			// 첨부파일이 있을 경우 해당 첨부파일의 확장자가 어떤 타입인지를 반환
			// image/png, image/jpg 등등으로 반환됨~
			String contextType = Files.probeContentType(file.toPath());
			return contextType.startsWith("image");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 산책 리뷰 이미지 자동 업로드
	@PostMapping(value="/reviewUpload", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<WalkReviewAttachFileVO>> reviewUploadPOST(MultipartFile[] uploadFile) throws Exception {
		log.info("reviewUploadPOST(uploadFile) 호출");
		
		// 파일의 변환된 정보 담을 리스트 객체 생성
		List<WalkReviewAttachFileVO> list = new ArrayList<>();
		// 파일 저장 위치
		String uploadFolder = "C:\\Users\\USER\\git\\nagaboka\\src\\main\\webapp\\resources\\upload";
		String uploadFolderPath = getFolder();
		
		// 업로드한 연/월/일로 폴더 생성하기 시작 --------------
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("업로드 위치: "+uploadPath );
		// 해당 폴더가 없을 경우 폴더 생성
		if(uploadPath.exists()==false) {
			uploadPath.mkdirs();
			log.info(uploadPath+" 패스 생성");
		}
		// 업로드한 연/월/일로 폴더 생성하기 끝 --------------
		
		// 파일이 여러개일 경우 하나씩 쪼개서 처리하기
		for(MultipartFile multi: uploadFile) {
			log.info("-----------------------------");
			
			// 파일 상세 정보 담을 객체 생성
			WalkReviewAttachFileVO attachVO = new WalkReviewAttachFileVO();
			
			log.info("업로드 파일명: "+multi.getOriginalFilename());
			log.info("업로드 파일 크기: "+multi.getSize());
			
			String uploadFileName = multi.getOriginalFilename();
			// 앞에서 .까지 잘라서 확장자를 제외한 파일명만 넣기
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("원래 파일 이름: "+uploadFileName);
			attachVO.setFileName(uploadFileName);
			
			// 중복 방지를 위한 "랜덤이름_원래이름" 생성 시작 -----------------
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+"_"+uploadFileName;
			// 중복 방지를 위한 "랜덤이름_원래이름" 생성 끝 ------------------
			
			try {
				// 연/월/일 폴더 만들어서 저장
				File saveFile = new File(uploadPath, uploadFileName);
				multi.transferTo(saveFile);
				
				attachVO.setUuid(uuid.toString());
				attachVO.setUploadPath(uploadFolderPath);
				
				// 이미지 파일 여부 확인 후 썸네일 생성 시작 -----------------
				if(checkImageType(saveFile)) {
					attachVO.setImage(true);
					// 이미지 파일일 경우 파일명 앞에 "s_"를 붙여서 새로운 파일명으로 내보내기
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_"+uploadFileName));
					
					// 내보낸 파일을 100*100으로 변환하여 썸네일 생성 후 파일 스트림 종료
					Thumbnailator.createThumbnail(multi.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				// 이미지 파일 여부 확인 후 썸네일 생성 끝 ------------------
				
				// 저장한 attachVO 리스트에 담기
				list.add(attachVO);
				
				log.info("파일 저장 완료");
			} catch (Exception e) {
				log.error(e.getMessage());
			} // catch
		} // for
		
		// for문으로 반복한 list 정보 반환
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 산책 리뷰 이미지 업로드 후 썸네일 출력
	@GetMapping(value="/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("파일명: "+fileName);
		
		String uploadFolder = "C:\\Users\\USER\\git\\nagaboka\\src\\main\\webapp\\resources\\upload";
		File file = new File(uploadFolder+"\\"+fileName);
		log.info("파일: "+file);
		
		ResponseEntity<byte[]> result = null;
		try {
			// 해당 정보 받아서 헤더에 담기
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
					header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}
