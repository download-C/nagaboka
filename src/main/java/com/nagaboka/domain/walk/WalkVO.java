package com.nagaboka.domain.walk;

import lombok.Data;

@Data
public class WalkVO {
	private int wno;
	private String wname;
	private double wlat;
	private double wlng;
	private int wlikecount;
	private int wrcount;
}
