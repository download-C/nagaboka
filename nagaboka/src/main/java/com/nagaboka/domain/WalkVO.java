package com.nagaboka.domain;

import lombok.Data;

@Data
public class WalkVO {
	private int wno;
	private String wname;
	private int wlikecount;
	private int wccount;
	private double lat;
	private double lng;
}
