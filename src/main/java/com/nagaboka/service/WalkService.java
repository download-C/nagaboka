package com.nagaboka.service;

import java.util.List;

import com.nagaboka.domain.walk.WalkVO;

public interface WalkService {

	List<WalkVO> getWalkList(double latitude, double longitude);

}
