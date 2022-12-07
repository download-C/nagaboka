package com.nagaboka.service;

import java.util.List;
import java.util.Map;

import com.nagaboka.domain.PageMakerVO;
import com.nagaboka.domain.PageVO;
import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;

public interface WalkService {

	public List<WalkVO> getWalkList(double latitude, double longitude) throws Exception;

	public WalkVO getWalk(String wname) throws Exception;

	public void writeWalkReview(WalkReviewVO review) throws Exception;

	public List<WalkReviewVO> getWalkReviewList(PageMakerVO pm, WalkVO walk) throws Exception;

	public int getWalkReviewCnt(WalkVO walk) throws Exception;

	public List<String> getWalkRevieImgList(WalkVO walk) throws Exception;



}
