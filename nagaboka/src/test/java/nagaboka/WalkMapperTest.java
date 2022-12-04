package nagaboka;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		log.info("♡♡♡♡♡♡♡♡♡♡테스트");
	}
	
//	@Test
	public void insertWalk() throws Exception {
		log.info("♡♡♡♡♡♡♡♡♡♡산책장소 입력");
		
		WalkVO walk = new WalkVO();
		walk.setWname("광안리해수욕장");
		
		session.insert(NAMESPACE+".insertWalk", walk);
		
		log.info("산책 정보 DB 저장 완료");
		
	}
	
	@Test
	public void selectWalkCnt() {
		log.info("♡♡♡♡♡♡♡♡♡♡산책장소 개수 세기");
		log.info("♡♡♡♡♡♡♡♡♡♡"+session.selectOne(NAMESPACE+".selectWalkCnt"));
	}
	
}
