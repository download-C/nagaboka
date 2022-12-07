package com.nagaboka.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nagaboka.domain.PageMakerVO;
import com.nagaboka.domain.PageVO;
import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;
import com.nagaboka.service.WalkService;

@Controller
@RequestMapping(value="/walk/*")
public class WalkController {
	
	@Inject
	private WalkService service;
	
	@Inject
	private HttpSession session;

	private static final Logger log = LoggerFactory.getLogger(WalkController.class);
	
	// http://localhost:8088/walk/walkMap
	@RequestMapping(value="/walkMap", method = RequestMethod.GET)
	public String walkMapGET()throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡ 1. controller - walkMapGET()");
		// 임시 위도 경도 -> 로그인한 회원의 DB에서 가져올 예정
//		double latitude = 35.172661;
//		double longitude = 129.108575;
		
		// 회원이 입력한 위도 및 경도를 기반으로 한 산책 장소 목록 가져오기 

		// 축척이 1km일 경우 반경 n 거리 안쪽일 때를 할까 생각 중
//		List<WalkVO> walkList = service.getWalkList(latitude, longitude);
		return "/walk/walkMap";
	}
	
	// http://localhost:8088/walk/write
	@GetMapping(value="/write")
	public String writeWalkReviewGET() throws Exception{
		log.info("♡♡♡♡♡♡♡♡♡♡walkWriateGET(walk) 호출");
		return "/walk/writeReview";
	}
	
	// 테스트 완료
	@PostMapping(value="/write") 
	public String  writeWalkReviewPOST(WalkReviewVO review, 
			HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡walkWriatePOST(walk) 호출");
		
		String w_name = request.getParameter("w_name");
		log.info("♡♡♡♡♡♡♡♡♡♡"+w_name+" 정보 불러오기 ");
		
		// 들어온 장소 이름으로 장소 정보 가져와 리뷰에 담기
		WalkVO walk = service.getWalk(w_name);
		log.info("♡♡♡♡♡♡♡♡♡♡"+w_name+" 정보: "+walk);
		review.setWalk(walk);
		
		log.info("♡♡♡♡♡♡♡♡♡♡ 작성한 리뷰 정보: "+review);
		
		// 첨부 이미지가 있을 때 정보 출력하기
		if(review.getAttachList()!=null) {
			review.getAttachList().forEach(attach -> log.info("파일 저장 경로: "+attach.toString()));
			String wr_imgs = "";
			for(int i=0; i<review.getAttachList().size(); i++) {
				wr_imgs += review.getAttachList().get(i).toString();
				wr_imgs += "$";
			}
			log.info("♡♡♡♡♡♡♡♡♡♡첨부파일 $ 기준으로 붙이기: "+wr_imgs.substring(0, wr_imgs.length()-1));
			// 맨 마지막 $ 빼고 set하기
			review.setWr_imgs(wr_imgs.substring(0, wr_imgs.length()-1));
		}
		
		// 로그인 구현되면 이걸루 바꾸기~
//		review.setU_id((String)session.getAttribute("u_id"));
		review.setU_id("admin");
		log.info("♡♡♡♡♡♡♡♡♡♡리뷰 정보: "+review);
		
		service.writeWalkReview(review);
		
		rttr.addFlashAttribute("msg1", "리뷰 작성 성공");
		
		return "redirect:/walk/walkReviewList";
	}
	
	// http://localhost:8088/walk/walkReviewList
	@GetMapping(value="/walkReviewList")
	public void walkReviewListGET(PageVO vo, WalkVO walk, Model model) throws Exception {
		log.info("walkReviewListGET() 호출");
		// 페이징 처리 하단부 정보 저장
		PageMakerVO pm = new PageMakerVO();
		pm.setVo(vo);
		
		walk.setW_name("광안리해수욕장"); // 임시로 광안리해수욕장으로 장소 지정 //
		
		int cnt = service.getWalkReviewCnt(walk);
		log.info(walk.getW_name()+" 리뷰 개수: "+cnt);
		
		pm.setTotalCnt(cnt);
		
		model.addAttribute("pm", pm);
		log.info("pm으로 페이징 처리 완료");
		
		List<WalkReviewVO> walkReviewList = service.getWalkReviewList(pm, walk);
		model.addAttribute("walkReviewList", walkReviewList);
		log.info(walk.getW_name()+" 리뷰 목록 부르기 성공");
	}
	
}
