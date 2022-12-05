package com.nagaboka.persistence;

import java.util.List;

import com.nagaboka.domain.WalkVO;

public interface WalkDAO {

	List<WalkVO> getWalkList(double latitude, double longitude);

}
