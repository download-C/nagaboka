package com.nagaboka.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nagaboka.domain.PageVO;
import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;
import com.nagaboka.persistence.WalkDAO;

@Service
public class WalkServiceImpl implements WalkService{

	private static final Logger log = LoggerFactory.getLogger(WalkServiceImpl.class);
	
	@Inject
	WalkDAO dao;

	@Override
	public List<WalkVO> getWalkList(double latitude, double longitude) throws Exception {
		log.info("getWalkList(latitude, longitude) 호출");
		
		return dao.getWalkList(latitude, longitude);
	}

	@Override
	public WalkVO getWalk(String wname) throws Exception {
		return dao.getWalk(wname);
	}

	@Override
	public void writeWalkReview(WalkReviewVO review) throws Exception{
		dao.writeWalkReview(review);
	}

	@Override
	public List<WalkReviewVO> getReviewList(PageVO vo) throws Exception {
		return null;
	}

	
	
	
	
}
