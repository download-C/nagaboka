package com.nagaboka.service;

import java.util.List;

import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;

public interface WalkService {

	public List<WalkVO> getWalkList(double latitude, double longitude);

	public WalkVO getWalk(String wname) throws Exception;

	public void writeWalkReview(WalkReviewVO review);



}
