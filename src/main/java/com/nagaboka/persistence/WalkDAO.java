package com.nagaboka.persistence;

import java.util.List;

import com.nagaboka.domain.PageMakerVO;
import com.nagaboka.domain.PageVO;
import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;

public interface WalkDAO {

	List<WalkVO> getWalkList(double latitude, double longitude);

	WalkVO getWalk(String wname) throws Exception;

	void writeWalkReview(WalkReviewVO review) throws Exception;

	List<WalkReviewVO> getWalkReviewList(PageMakerVO pm) throws Exception;

	int getWalkReviewCnt(WalkVO walk) throws Exception;



}
