package com.nagaboka.persistence;

import java.util.List;

import com.nagaboka.domain.walk.WalkReviewVO;
import com.nagaboka.domain.walk.WalkVO;

public interface WalkDAO {

	List<WalkVO> getWalkList(double latitude, double longitude);

	WalkVO getWalk(String wname) throws Exception;

	void writeWalkReview(WalkReviewVO review);



}
