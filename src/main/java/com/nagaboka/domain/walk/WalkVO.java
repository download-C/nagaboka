package com.nagaboka.domain.walk;

import lombok.Data;

@Data
public class WalkVO {
	private int w_no;
	private String w_name;
	private double w_lat;
	private double w_lng;
	private int w_likecount;
	private int wr_count;
}
