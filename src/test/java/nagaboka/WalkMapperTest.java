package nagaboka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nagaboka.domain.PageVO;
import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;
import com.nagaboka.service.WalkService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class WalkMapperTest {
	
	private static final Logger log = LoggerFactory.getLogger(WalkMapperTest.class);
	
	@Inject
	SqlSession session;
	
	@Inject
	WalkService service;

	final static String NAMESPACE = "com.nagaboka.mapper.WalkMapper";
	
//	@Test
	public void test() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡test() 테스트");
	}
	
//	@Test
	public void insertWalk() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡insertWalk() 테스트");
		
		WalkVO walk = new WalkVO();
		walk.setW_name("삼락생태공원");
		walk.setW_lat(35.162285);
		walk.setW_lng(128.971192);
		
		
		
		session.insert(NAMESPACE+".insertWalk", walk);
		
		log.info("♡♡♡♡♡♡♡♡♡♡산책 장소 DB 저장 완료");
		
	}
	
//	@Test
	public void selectWalkCnt() {
		log.info("getWalkCnt() 테스트");
		log.info("♡♡♡♡♡♡♡♡♡♡장소 개수: "+session.selectOne(NAMESPACE+".getWalkCnt"));
	}
	
//	@Test
	public void insertWCCon() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡ insertWcon() 테스트");
		
		WalkVO walk = session.selectOne(NAMESPACE+".getWalk", "삼락생태공원");
		

		WalkReviewVO review = new WalkReviewVO();
		review.setU_id("Rang2");
		review.setWalk(walk);
		review.setWr_con("낙동강 똥바람이 불지만 좋네요");
		review.setWr_like(true);
		
		session.insert(NAMESPACE+".insertreview", review);
		
		log.info("♡♡♡♡♡♡♡♡♡♡ 댓글 남기기 완료");
	}
	 
//	@Test
	public void getWalkList() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡ getWalkList() 호출");
		WalkVO walk = session.selectOne(NAMESPACE+".getWalk", "삼락생태공원");
		log.info("♡♡♡♡♡♡♡♡♡♡ 가져온 장소 정보: "+walk);
		List<WalkVO> walkList = session.selectList(NAMESPACE+".getWalkList", walk);
		log.info("♡♡♡♡♡♡♡♡♡♡ 리스트 목록 : "+walkList);
	}
	
//	@Test
	public void getWalkCnt() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡ getWalkCnt() 호출");
		
		double latitude = 35.2;
		double longitude = 129.5;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		log.info("♡♡♡♡♡♡♡♡♡♡  글 개수 :"+session.selectOne(NAMESPACE+".getWalkCnt"), map);
	}
	
//	@Test
	public void getWalkReviewList() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡ getWalkReviewList() 호출");
		
		PageVO vo = new PageVO();
		
		int pageSize = 5;
		int currentPage = 1;
		
		WalkVO walk = session.selectOne(NAMESPACE+".getWalk", "광안리해수욕장");
		
		log.info(walk.getW_name()+" 정보 가져오기 성공");
		// 전체 글 개수
		int cnt = session.selectOne(NAMESPACE+".getWalkReviewCnt", walk);
		

		
		session.selectList(NAMESPACE+".getWalkReviewList", vo);
	}
	
//	@Test 
	public void getImgs() throws Exception {
		
		log.info("리스트 불러서 이미지 파일 자르기");
//		List<String> imgs = new ArrayList<String>();
//		imgs.add("2022\\12\\07\\858caf5f_2.jpg$2022\\12\\07\\2c5997db_584.jpg");
//		imgs.add("2022\\12\\07\\2f2b09d5_2.jpg$2022\\12\\07\\6b049962_584.jpg");
//		for(int i=0; i<imgs.size(); i++) {
//			String[] images = imgs.get(i).split("$");
//			for(String img: images) {
//				log.info("$로 자른 이미지 파일 이름: "+img);
//			}
//		}
		
		String wr_imgs = "2022\\12\\07\\858caf5f_2.jpg-2022\\12\\07\\2c5997db_584.jpg";
		String[] imgs = wr_imgs.split("-");
		for(String img: imgs) {
			log.info("자름 "+img);
		}
	}
	
//	@Test
	public void getWalkReviewImgAll() throws Exception{

	}
	
	
}
