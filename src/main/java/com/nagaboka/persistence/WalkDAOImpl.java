package com.nagaboka.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.nagaboka.domain.PageMakerVO;
import com.nagaboka.domain.PageVO;
import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;

@Repository
public class WalkDAOImpl implements WalkDAO{

	private static final Logger log = LoggerFactory.getLogger(WalkDAOImpl.class);
	
	private final static String NAMESPACE = "com.nagaboka.mapper.WalkMapper";
	
	@Inject
	SqlSession session;

	@Override
	public List<WalkVO> getWalkList(double latitude, double longitude) {
		log.info("getWalkList(latitude, longitude) 호출");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		return session.selectList(NAMESPACE+".getWalkList", map);
	}

	@Override
	public WalkVO getWalk(String wname) throws Exception {
		return session.selectOne(NAMESPACE+".getWalk", wname);
	}

	@Override
	public void writeWalkReview(WalkReviewVO review) {
		session.insert(NAMESPACE+".writeWalkReview", review);
	}

	@Override
	public List<WalkReviewVO> getWalkReviewList(PageMakerVO pm) throws Exception {
		return session.selectList(NAMESPACE+".getWalkReviewList", pm);
	}

	@Override
	public int getWalkReviewCnt(WalkVO walk) throws Exception {
		return session.selectOne(NAMESPACE+".getWalkReviewCnt", walk);
	}
	
	
}
