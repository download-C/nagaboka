package nagaboka;

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

import com.nagaboka.domain.WalkCommentVO;
import com.nagaboka.domain.WalkVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class WalkMapperTest {
	
	private static final Logger log = LoggerFactory.getLogger(WalkMapperTest.class);
	
	@Inject
	SqlSession session;

	final static String NAMESPACE = "com.nagaboka.mapper.WalkMapper";
	
//	@Test
	public void test() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡test() 테스트");
	}
	
//	@Test
	public void insertWalk() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡insertWalk() 테스트");
		
		WalkVO walk = new WalkVO();
		walk.setWname("광안리해수욕장");
		
		session.insert(NAMESPACE+".insertWalk", walk);
		
		log.info("♡♡♡♡♡♡♡♡♡♡산책 장소 DB 저장 완료");
		
	}
	
//	@Test
	public void selectWalkCnt() {
		log.info("selectWalkCnt() 테스트");
		log.info("♡♡♡♡♡♡♡♡♡♡장소 개수: "+session.selectOne(NAMESPACE+".selectWalkCnt"));
	}
	
//	@Test
	public void insertWCCon() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡ insertWcon() 테스트");
		
		WalkVO walk = session.selectOne(NAMESPACE+".getWalk", "광안리해수욕장");
		
		WalkCommentVO comment = new WalkCommentVO();
		comment.setUsername("admin");
		comment.setWalk(walk);
		comment.setWccon("다녀오니까 좋았어여~");
		comment.setWclike(true);
		
		session.insert(NAMESPACE+".insertComment", comment);
		
		log.info("♡♡♡♡♡♡♡♡♡♡ 댓글 남기기 완료");
	}
	 
//	@Test
	public void getWalkList() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡ getWalkList() 호출");
		WalkVO walk = session.selectOne(NAMESPACE+".getWalk", "광안리해수욕장");
		log.info("♡♡♡♡♡♡♡♡♡♡ 가져온 장소 정보: "+walk);
		List<WalkVO> walkList = session.selectList(NAMESPACE+".getWalkList", walk);
		log.info("♡♡♡♡♡♡♡♡♡♡ 리스트 목록 : "+walkList);
	}
	
	@Test
	public void getWalkCnt() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡ getWalkCnt() 호출");
		
		double latitude = 35.2;
		double longitude = 129.5;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		log.info("♡♡♡♡♡♡♡♡♡♡  글 개수 :"+session.selectOne(NAMESPACE+".getWalkCnt"), map);
	}
	
}
