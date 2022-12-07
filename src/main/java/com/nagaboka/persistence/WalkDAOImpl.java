package com.nagaboka.persistence;

import java.util.ArrayList;
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
		log.info("작성한 리뷰 정보: "+review);
		session.insert(NAMESPACE+".writeWalkReview", review);
	}

	@Override
	public List<WalkReviewVO> getWalkReviewList(PageMakerVO pm, WalkVO walk) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("walk", walk);
		map.put("pm", pm);
		return session.selectList(NAMESPACE+".getWalkReviewList", map);
	}

	@Override
	public int getWalkReviewCnt(WalkVO walk) throws Exception {
		return session.selectOne(NAMESPACE+".getWalkReviewCnt", walk);
	}

	@Override
	public List<String> getWalkReviewImgList(WalkVO walk) throws Exception {
		String[] imgs = null; 
		
		List<String> imgList = new ArrayList<>();
		List<String> thumbnailList = new ArrayList<>();

		String w_name = "광안리해수욕장";
		
		walk = session.selectOne(NAMESPACE+".getWalk", w_name);
		List<String> walkReviewImgAll = session.selectList(NAMESPACE+".getWalkReviewImgAll", walk);
		log.info("가져온 리뷰 정보: "+walkReviewImgAll);
		for(int i=0; i<walkReviewImgAll.size(); i++) {
			imgs = walkReviewImgAll.get(i).split("-");
			for(String img: imgs) {
//				log.info("이미지 파일: "+img);
//				imgList.add(img);
				
				String uploadPath = img.substring(0, 11);
				String uuid = img.substring(11,19);
				String fileName = img.substring(20);
				log.info(uploadPath);
				log.info(uuid);
				log.info(fileName);
				thumbnailList.add(uploadPath+"s_"+uuid+"_"+fileName);
			}
		}
		log.info("하나씩 자른 이미지 목록: "+imgList);
		log.info("썸네일 목록:" +thumbnailList);

		return thumbnailList;
	}
	
	
}
